package com.javaweb.controller.admin;

import com.javaweb.model.dto.SellerRequestDTO;
import com.javaweb.service.SellerRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/admin/seller-request")
@PreAuthorize("hasRole('ADMIN')")
public class SellerRequestController {

    @Autowired
    private SellerRequestService sellerRequestService;

    @GetMapping("/pending")
    public List<SellerRequestDTO> getPending() {
        return sellerRequestService.getPendingRequests();
    }

    @PostMapping("/{id}/approve")
    public void approve(@PathVariable Long id, Authentication auth) {
        sellerRequestService.approve(id, auth.getName());
    }

    @PostMapping("/{id}/reject")
    public void reject(@PathVariable Long id, @RequestBody String note) {
        sellerRequestService.reject(id, note);
    }
}

