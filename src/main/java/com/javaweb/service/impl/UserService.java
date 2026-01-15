package com.javaweb.service.impl;

import com.javaweb.constant.SystemConstant;
import com.javaweb.converter.UserConverter;
import com.javaweb.entity.CustomerEntity;
import com.javaweb.entity.SellerRequestEntity;
import com.javaweb.model.dto.CustomerDTO;
import com.javaweb.model.dto.PasswordDTO;
import com.javaweb.model.dto.UserDTO;
import com.javaweb.entity.RoleEntity;
import com.javaweb.entity.UserEntity;
import com.javaweb.exception.MyException;
import com.javaweb.repository.RoleRepository;
import com.javaweb.repository.SellerRequestRepository;
import com.javaweb.repository.UserRepository;
import com.javaweb.service.IUserService;
import org.apache.commons.lang.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserConverter userConverter;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private SellerRequestRepository sellerRequestRepository;


    @Override
    public UserDTO findOneByUserNameAndStatus(String name, int status) {
        UserEntity entity = userRepository.findOneByUserNameAndStatus(name, status);
        if (entity == null) {
            return null;
        }
        return userConverter.convertToDto(entity);
    }

    @Override
    public List<UserDTO> getUsers(String searchValue, Pageable pageable) {
        Page<UserEntity> users = null;
        if (StringUtils.isNotBlank(searchValue)) {
            users = userRepository.findByUserNameContainingIgnoreCaseOrFullNameContainingIgnoreCaseAndStatusNot(searchValue, searchValue, 0, pageable);
        } else {
            users = userRepository.findByStatusNot(0, pageable);
        }
        List<UserEntity> newsEntities = users.getContent();
        List<UserDTO> result = new ArrayList<>();
        for (UserEntity userEntity : newsEntities) {
            UserDTO userDTO = userConverter.convertToDto(userEntity);
            userDTO.setRoleCode(userEntity.getRoles().get(0).getCode());
            result.add(userDTO);
        }
        return result;
    }





//    @Override
//    public List<UserDTO> getAllUsers(Pageable pageable) {
//        List<UserEntity> userEntities = userRepository.getAllUsers(pageable);
//        List<UserDTO> results = new ArrayList<>();
//        for (UserEntity userEntity : userEntities) {
//            UserDTO userDTO = userConverter.convertToDto(userEntity);
//            userDTO.setRoleCode(userEntity.getRoles().get(0).getCode());
//            results.add(userDTO);
//        }
//        return results;
//    }

    @Override
    public int countTotalItems() {
        return userRepository.countTotalItem();
    }



    @Override
    public int getTotalItems(String searchValue) {
        int totalItem = 0;
        if (StringUtils.isNotBlank(searchValue)) {
            totalItem = (int) userRepository.countByUserNameContainingIgnoreCaseOrFullNameContainingIgnoreCaseAndStatusNot(searchValue, searchValue, 0);
        } else {
            totalItem = (int) userRepository.countByStatusNot(0);
        }
        return totalItem;
    }

    @Override
    public UserDTO findOneByUserName(String userName) {
        UserEntity userEntity = userRepository.findOneByUserName(userName);
        UserDTO userDTO = userConverter.convertToDto(userEntity);
        return userDTO;
    }

    @Override
    public UserDTO findUserById(long id) {
        UserEntity entity = userRepository.findById(id).get();
        List<RoleEntity> roles = entity.getRoles();
        UserDTO dto = userConverter.convertToDto(entity);
        roles.forEach(item -> {
            dto.setRoleCode(item.getCode());
        });
        return dto;
    }

    @Override
    @Transactional
    public UserDTO insert(UserDTO newUser) {

        // 1. Lấy role
        RoleEntity role = roleRepository.findOneByCode(newUser.getRoleCode());

        // 2. Convert DTO → Entity
        UserEntity userEntity = userConverter.convertToEntity(newUser);
        userEntity.setRoles(List.of(role));
        userEntity.setStatus(1);
        userEntity.setPassword(passwordEncoder.encode(newUser.getPassword()));

        // 3. LƯU USER TRƯỚC để có ID
        userEntity = userRepository.save(userEntity);

        // 4. Nếu user tick "đăng ký làm người bán"
        if (Boolean.TRUE.equals(newUser.getRegisterSeller())) {
            SellerRequestEntity request = new SellerRequestEntity();
            request.setUser(userEntity);
            request.setStatus("PENDING");
            request.setCreatedDate(LocalDateTime.now());
            sellerRequestRepository.save(request);
        }


        // 5. Trả về DTO
        return userConverter.convertToDto(userEntity);
    }


    @Override
    @Transactional
    public UserDTO update(Long id, UserDTO updateUser) {
        RoleEntity role = roleRepository.findOneByCode(updateUser.getRoleCode());
        UserEntity oldUser = userRepository.findById(id).get();
        UserEntity userEntity = userConverter.convertToEntity(updateUser);
        userEntity.setUserName(oldUser.getUserName());
        userEntity.setStatus(oldUser.getStatus());
        userEntity.setRoles(Stream.of(role).collect(Collectors.toList()));
        userEntity.setPassword(oldUser.getPassword());
        return userConverter.convertToDto(userRepository.save(userEntity));
    }

    @Override
    @Transactional
    public void updatePassword(long id, PasswordDTO passwordDTO) throws MyException {
        UserEntity user = userRepository.findById(id).get();
        if (passwordEncoder.matches(passwordDTO.getOldPassword(), user.getPassword())
                && passwordDTO.getNewPassword().equals(passwordDTO.getConfirmPassword())) {
            user.setPassword(passwordEncoder.encode(passwordDTO.getNewPassword()));
            userRepository.save(user);
        } else {
            throw new MyException(SystemConstant.CHANGE_PASSWORD_FAIL);
        }
    }

    @Override
    @Transactional
    public UserDTO resetPassword(long id) {
        UserEntity userEntity = userRepository.findById(id).get();
        userEntity.setPassword(passwordEncoder.encode(SystemConstant.PASSWORD_DEFAULT));
        return userConverter.convertToDto(userRepository.save(userEntity));
    }

    @Override
    @Transactional
    public UserDTO updateProfileOfUser(String username, UserDTO updateUser) {
        UserEntity oldUser = userRepository.findOneByUserName(username);
        oldUser.setFullName(updateUser.getFullName());
        return userConverter.convertToDto(userRepository.save(oldUser));
    }

    @Override
    @Transactional
    public void delete(long[] ids) {
        List<RoleEntity> roleEntities = roleRepository.findByIdIn(ids);
        for (Long item : ids) {
            UserEntity userEntity = userRepository.findById(item).get();
            userRepository.deleteAllByRolesIn(roleEntities);
            userEntity.setStatus(0);
            userRepository.save(userEntity);
        }
    }

    @Override
    public List<UserDTO> liststaff() {
        List<UserEntity> userEntities = userRepository.findByStatusAndRoles_Code(1, "STAFF");
        List<UserDTO> result = new ArrayList<>();

        for (UserEntity entity : userEntities) {
            UserDTO dto = new UserDTO();
            dto.setId(entity.getId());
            dto.setFullName(entity.getFullName()); // hoặc getUserName tuỳ UI
            result.add(dto);
        }

        return result;
    }

    @Override
    public List<UserDTO> findAll() {
        return userRepository.findAll()
                .stream()
                .map(userConverter::convertToDto)  // nếu có converter
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO findById(Long id) throws MyException {
        UserEntity entity = userRepository.findById(id)
                .orElseThrow(() -> new MyException("Không tìm thấy người dùng"));
        return modelMapper.map(entity, UserDTO.class);
    }

    @Override
    public List<UserDTO> searchUsers(String userName, String fullName, String email) {
        return userRepository.searchUsers(userName, fullName, email)
                .stream()
                .map(userConverter::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public String uploadAvatar(long id, MultipartFile file) throws MyException, IOException {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new MyException("Không tìm thấy người dùng"));

        String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
        Path uploadPath = Paths.get("uploads/avatars");

        if (!Files.exists(uploadPath)) Files.createDirectories(uploadPath);

        Path filePath = uploadPath.resolve(fileName);
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        String fileUrl = "/uploads/avatars/" + fileName;
        user.setAvatar(fileUrl);
        System.out.println(fileUrl);
        userRepository.save(user);

        return fileUrl;
    }



}