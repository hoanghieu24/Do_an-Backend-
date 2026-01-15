package com.javaweb.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javaweb.security.utils.SecurityUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.List;

@Component
public class CustomSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException {
        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        var principal = SecurityUtils.getPrincipal();

        List<String> roles = SecurityUtils.getAuthorities();
        String fullname = principal.getFullName();  // ✅ không bọc list
        Long id = principal.getId();                // ✅ không bọc list
        String avatar = principal.getAvatar();

        if (avatar == null) {
            avatar = "https://cdn.vectorstock.com/i/1000v/92/16/default-profile-picture-avatar-user-icon-vector-46389216.jpg";
        }

        Map<String, Object> responseData = Map.of(
                "status", "success",
                "roles", roles,
                "fullname", fullname,
                "avatar", avatar,
                "id", id // 🟢 đổi "customerid" → "id" để thống nhất frontend
        );

        new ObjectMapper().writeValue(response.getWriter(), responseData);
    }
}
