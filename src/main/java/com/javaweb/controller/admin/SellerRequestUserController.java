package com.javaweb.controller.admin;

import com.javaweb.model.dto.SellerRequestDTO;
import com.javaweb.service.SellerRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/seller-requests") // user gửi request
public class SellerRequestUserController {

    @Autowired
    private SellerRequestService sellerRequestService;

    @PostMapping("/")
    public SellerRequestDTO createRequest(@RequestBody SellerRequestDTO dto) {
        return sellerRequestService.createSellerRequest(dto);
    }
}

