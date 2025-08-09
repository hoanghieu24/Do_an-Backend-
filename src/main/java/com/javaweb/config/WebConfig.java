package com.javaweb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Áp dụng cho tất cả các API
                .allowedOrigins("http://localhost:5173") // Địa chỉ frontend
                .allowedMethods("GET", "POST", "PUT", "DELETE") // Các phương thức HTTP được phép
                .allowedHeaders("*") // Cho phép tất cả các header
                .allowCredentials(true); // Cho phép cookies nếu cần
    }

//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        // Mapping URL /uploads/** tới thư mục trên máy
//        registry.addResourceHandler("/uploads/**")
//                .addResourceLocations("file:uploads/"); // nếu nằm trong project
//        // hoặc "file:/absolute/path/to/uploads/" nếu nằm ngoài
//    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/img/**")
                .addResourceLocations("file:///C:/Users/Nitro%205/Desktop/DO_An_2/do_an/img/");
    }


    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
