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

        List<String> roles = SecurityUtils.getAuthorities();
        List<String> fullname = Collections.singletonList(SecurityUtils.getPrincipal().getFullName());
        List<Long> customerId = Collections.singletonList(SecurityUtils.getPrincipal().getId());

        String json = new ObjectMapper().writeValueAsString(Map.of(
                "status", "success",
                "roles", roles,
                    "fullname",fullname,
                "customerid",customerId
        ));
        response.getWriter().write(json);
    }

}