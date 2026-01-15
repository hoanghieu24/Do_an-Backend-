package com.javaweb.model.dto;

import lombok.Data;

@Data
public class SellerRequestDTO {
    private Long id;
    private Long userId;
    private String userName;
    private String fullName;
    private String status;
    private String note;
    private String createdDate;
    private String phoneNumber;
    private String email;
}

