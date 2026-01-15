package com.javaweb.service.impl;

import com.javaweb.model.dto.MyUserDetail;
import com.javaweb.model.dto.RoleDTO;
import com.javaweb.model.dto.UserDTO;
import com.javaweb.service.IUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private IUserService userService;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        System.out.println("🔍 Đang load user với username: " + name);

        UserDTO userDTO = userService.findOneByUserNameAndStatus(name, 1);

        if (userDTO == null) {
            System.out.println("❌ Không tìm thấy user hoặc user bị vô hiệu hóa");
            throw new UsernameNotFoundException("User not found or status != 1");
        }

        System.out.println("👉 Password trong DB: " + userDTO.getPassword());
        System.out.println("👉 Các quyền (roles): " + userDTO.getRoles());
        System.out.println("👉 Họ tên: " + userDTO.getFullName());
        System.out.println("👉 ID: " + userDTO.getId());
        System.out.println("👉 Avatar: " + userDTO.getAvatar());

        if (userDTO.getRoles() == null || userDTO.getRoles().isEmpty()) {
            System.out.println("⚠️ User không có quyền nào được gán");
            throw new UsernameNotFoundException("User has no valid roles");
        }

        List<GrantedAuthority> authorities = userDTO.getRoles().stream()
                .filter(Objects::nonNull)
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getCode()))
                .collect(Collectors.toList());

        MyUserDetail myUserDetail = new MyUserDetail(
                name,
                userDTO.getPassword(),
                true, true, true, true,
                authorities
        );

        myUserDetail.setId(userDTO.getId());
        myUserDetail.setFullName(userDTO.getFullName());

        // ✅ Cho phép avatar null, dùng Optional để tránh lỗi
        myUserDetail.setAvatar(
                userDTO.getAvatar() != null ? userDTO.getAvatar() : null
        );

        // Nếu ông muốn có ảnh mặc định khi avatar null (gợi ý)
        // myUserDetail.setAvatar(
        //         userDTO.getAvatar() != null ? userDTO.getAvatar() : "/img/default_avatar.png"
        // );

        return myUserDetail;
    }
}

