package com.javaweb.service.impl;

import com.javaweb.entity.RoleEntity;
import com.javaweb.entity.SellerRequestEntity;
import com.javaweb.entity.UserEntity;
import com.javaweb.model.dto.SellerRequestDTO;
import com.javaweb.repository.RoleRepository;
import com.javaweb.repository.SellerRequestRepository;
import com.javaweb.repository.UserRepository;
import com.javaweb.service.SellerRequestService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class SellerRequestServiceImpl implements SellerRequestService {

    @Autowired
    private SellerRequestRepository sellerRequestRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<SellerRequestDTO> getPendingRequests() {
        return sellerRequestRepository.findByStatus("PENDING")
                .stream()
                .map(req -> {
                    SellerRequestDTO dto = new SellerRequestDTO();
                    dto.setId(req.getId());
                    dto.setUserId(req.getUser().getId());
                    dto.setUserName(req.getUser().getUserName());
                    dto.setFullName(req.getUser().getFullName());
                    dto.setStatus(req.getStatus());
                    dto.setCreatedDate(req.getCreatedDate().toString());
                    return dto;
                }).toList();
    }


    public void approve(Long requestId, String adminUsername) {
        SellerRequestEntity request = sellerRequestRepository.findById(requestId).get();

        UserEntity user = request.getUser();
        RoleEntity staffRole = roleRepository.findOneByCode("STAFF");

        // Gán quyền
        user.getRoles().clear();
        user.getRoles().add(staffRole);
        userRepository.save(user);

        // Update request
        request.setStatus("APPROVED");
        request.setApprovedBy(userRepository.findOneByUserName(adminUsername));
        request.setApprovedDate(LocalDateTime.now());
        sellerRequestRepository.save(request);
    }

    public void reject(Long id, String note) {
        SellerRequestEntity request = sellerRequestRepository.findById(id).get();
        request.setStatus("REJECTED");
        request.setNote(note);
        sellerRequestRepository.save(request);
    }

    @Override
    public SellerRequestDTO createSellerRequest(SellerRequestDTO dto) {
        SellerRequestEntity request = new SellerRequestEntity();
        request.setUser(userRepository.findById(dto.getUserId()).orElseThrow());
        request.setUserName(dto.getUserName());
        request.setFullName(dto.getFullName());
        request.setEmail(dto.getEmail());
        request.setPhoneNumber(dto.getPhoneNumber());
//        request.setAddress(dto.getAddress());
//        request.setDescription(dto.getDescription());
        request.setStatus("PENDING");
        request.setCreatedDate(LocalDateTime.now());
        sellerRequestRepository.save(request);

        return modelMapper.map(request, SellerRequestDTO.class);
    }


}

