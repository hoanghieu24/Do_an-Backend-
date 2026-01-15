package com.javaweb.service;

import com.javaweb.model.dto.SellerRequestDTO;

import java.util.List;

public interface SellerRequestService {
    List<SellerRequestDTO> getPendingRequests();
    void approve(Long id, String adminUsername);
    void reject(Long id, String note);
    SellerRequestDTO createSellerRequest(SellerRequestDTO dto);
}

