package com.javaweb.api.admin;

import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.ContractRepository;
import com.javaweb.repository.CustomerRepository;
import com.javaweb.service.impl.DashboardServiceimpl;
import com.javaweb.utils.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.*;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminDashboardAPI {

    private final DashboardServiceimpl dashboardService;

    @Autowired
    private final CustomerRepository customerRepository;
    @Autowired
    private final BuildingRepository buildingRepository;
    @Autowired
    private final ContractRepository contractRepository;

    // 1️⃣ Tổng hợp toàn bộ thống kê
//    @GetMapping("/stats")
//    public ResponseEntity<?> getDashboardStats() {
//        Map<String, Object> stats = dashboardService.getDashboardStats();
//        return ResponseEntity.ok(stats);
//    }

    // 2️⃣ Hoạt động gần đây (vd: hợp đồng mới, khách hàng mới,…)
//    @GetMapping("/activities")
//    public ResponseEntity<?> getRecentActivities() {
//        return ResponseEntity.ok(dashboardService.getRecentActivities());
//    }

    // 3️⃣ Sự kiện sắp tới (vd: hợp đồng sắp hết hạn,…)
//    @GetMapping("/events")
//    public ResponseEntity<?> getUpcomingEvents() {
//        return ResponseEntity.ok(dashboardService.getUpcomingEvents());
//    }

    @GetMapping("/revenue/monthly")
    public Map<String, Object> getMonthlyRevenue() {
        Long currentUserId = SecurityUtils.getCurrentUserId();
        boolean isAdmin = SecurityUtils.isAdmin();
        boolean isUsers = SecurityUtils.isUser();
        long count = contractRepository.getMonthlyRevenue(currentUserId, isAdmin);
        return Map.of("amount", count);
    }


    @GetMapping("/buildings/count")
    public Map<String, Object> countBuildings() {
        return Map.of("count", buildingRepository.count());
    }


    @GetMapping("/customers/count")
    public Map<String, Object> countCustomers() {
        return Map.of("count", customerRepository.count());
    }


    @GetMapping("/contracts/active/count")
    public Map<String, Object> countActiveContracts() {
        Long currentUserId = SecurityUtils.getCurrentUserId();
        boolean isAdmin = SecurityUtils.isAdmin();
        boolean isUsers = SecurityUtils.isUser();
        long count = contractRepository.countActiveContracts(currentUserId, isAdmin);

        return Map.of("count", count);
    }




}
