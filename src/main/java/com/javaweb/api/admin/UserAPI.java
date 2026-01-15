package com.javaweb.api.admin;

import com.javaweb.constant.SystemConstant;
import com.javaweb.exception.MyException;
import com.javaweb.model.dto.FavouriteDTO;
import com.javaweb.model.dto.MyUserDetail;
import com.javaweb.model.dto.PasswordDTO;
import com.javaweb.model.dto.UserDTO;
import com.javaweb.service.IUserService;
import com.javaweb.service.impl.CustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
public class UserAPI {

    @Autowired
    private IUserService userService;

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers(
            @RequestParam(value = "userName", required = false) String userName,
            @RequestParam(value = "fullName", required = false) String fullName,
            @RequestParam(value = "email", required = false) String email) {

        List<UserDTO> users = userService.searchUsers(userName, fullName, email);
        System.out.println(">>> searchUsers called with: " + userName + " - " + fullName + " - " + email);
        return ResponseEntity.ok(users);
    }


    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable("id") long id) throws MyException {
        return ResponseEntity.ok(userService.findById(id));
    }



    @PostMapping
    public ResponseEntity<UserDTO> createUsers(@RequestBody UserDTO newUser) {
        return ResponseEntity.ok(userService.insert(newUser));
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<UserDTO> updateUsers(@PathVariable("id") long id, @RequestBody UserDTO userDTO) throws MyException {
//        return ResponseEntity.ok(userService.update(Long.valueOf(id), userDTO));
//    }

    @PutMapping("/change-password/{id}")
    public ResponseEntity<String> changePasswordUser(@PathVariable("id") long id, @RequestBody PasswordDTO passwordDTO) {
        try {
            userService.updatePassword(id, passwordDTO);
            return ResponseEntity.ok(SystemConstant.UPDATE_SUCCESS);
        } catch (MyException e) {
            //LOGGER.error(e.getMessage());
            return ResponseEntity.ok(e.getMessage());
        }
    }

    @PutMapping("/password/{id}/reset")
    public ResponseEntity<UserDTO> resetPassword(@PathVariable("id") long id) throws MyException {
        return ResponseEntity.ok(userService.resetPassword(id));
    }

    @PutMapping("/profile/{username}")
    public ResponseEntity<UserDTO> updateProfileOfUser(@PathVariable("username") String username, @RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(userService.updateProfileOfUser(username, userDTO));
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteUsers(@RequestBody long[] idList) throws MyException {
        if (idList.length > 0) {
            userService.delete(idList);
        }
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/me")
    public ResponseEntity<?> getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        MyUserDetail user = (MyUserDetail) auth.getPrincipal();

        Map<String, Object> result = new HashMap<>();
        result.put("username", user.getUsername());
        result.put("avatar", user.getAvatar());

        // 🔥 Lấy danh sách các ROLE CODE
        List<String> roleCodes = user.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority) // hoặc có thể là .getCode() nếu bạn custom
                .collect(Collectors.toList());

        result.put("roles", roleCodes);
//        result.put("fullname", user.getFullName());
        result.put("customerid",user.getId());

        return ResponseEntity.ok(result);
    }
    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUsers(
            @PathVariable("id") long id,
            @RequestPart(value = "user", required = true) UserDTO userDTO,
            @RequestPart(value = "file", required = false) MultipartFile file
    ) throws IOException, MyException {

        // ✅ Nếu có file => upload avatar trước
        if (file != null && !file.isEmpty()) {
            String avatarUrl = userService.uploadAvatar(id, file);
            userDTO.setAvatar(avatarUrl);
        }

        // ✅ Update thông tin user trong DB
        UserDTO updatedUser = userService.update(id, userDTO);

        // ✅ Lấy Authentication hiện tại
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();

        // ✅ Nếu user đang login là MyUserDetail thì update ngay trong session
        if (principal instanceof MyUserDetail) {
            MyUserDetail currentUser = (MyUserDetail) principal;

            // Kiểm tra nếu user hiện tại đang cập nhật chính mình
            if (currentUser.getId() == id) {
                currentUser.setFullName(updatedUser.getFullName());
                currentUser.setAvatar(updatedUser.getAvatar());
                // 👉 có thể thêm các field khác nếu cần: email, phone, v.v.

                // 🔁 Tạo Authentication mới với thông tin vừa cập nhật
                UsernamePasswordAuthenticationToken newAuth =
                        new UsernamePasswordAuthenticationToken(
                                currentUser,
                                authentication.getCredentials(),
                                authentication.getAuthorities()
                        );

                // ✅ Cập nhật lại SecurityContextHolder
                SecurityContextHolder.getContext().setAuthentication(newAuth);

                System.out.println("🔄 Đã cập nhật SecurityContext với avatar mới cho user: " + currentUser.getUsername());
            }
        }

        return ResponseEntity.ok(updatedUser);
    }








}
