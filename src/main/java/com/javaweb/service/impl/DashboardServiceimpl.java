package com.javaweb.service.impl;

import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.ContractRepository;
import com.javaweb.repository.CustomerRepository;
import com.javaweb.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class DashboardServiceimpl {

    private final CustomerRepository customerRepository;
    private final BuildingRepository buildingRepository;
    private final ContractRepository contractRepository;
    private final UserRepository userRepository;
//    private final RevenueRepository revenueRepository;

    public Map<String, Object> getDashboardStats() {
        Map<String, Object> data = new HashMap<>();

        data.put("totalCustomers", customerRepository.count());
        data.put("totalBuildings", buildingRepository.count());
//        data.put("activeContracts", contractRepository.countByContractCode("ACTIVE"));
//        data.put("monthlyRevenue", revenueRepository.sumRevenueForCurrentMonth());

        return data;
    }



//    public List<Map<String, Object>> getRecentActivities() {
//        // Giả lập hoặc query thật
//        return List.of(
//                Map.of("type", "contract", "message", "Hợp đồng mới được ký ngày 10/10", "time", "2 giờ trước"),
//                Map.of("type", "customer", "message", "Khách hàng Nguyễn A vừa đăng ký", "time", "5 giờ trước")
//        );
//    }
//
//    public List<Map<String, Object>> getUpcomingEvents() {
//        return List.of(
//                Map.of("event", "Hợp đồng C123 sắp hết hạn", "date", "2025-10-20"),
//                Map.of("event", "Tòa nhà B09 cần bảo trì", "date", "2025-10-22")
//        );
//    }
//
//    public List<Map<String, Object>> getMonthlyRevenue() {
//        // Ví dụ: query theo tháng, group by MONTH
//        return List.of(
//                Map.of("month", "Jan", "revenue", 12000000),
//                Map.of("month", "Feb", "revenue", 17500000),
//                Map.of("month", "Mar", "revenue", 9000000)
//        );
//    }
}
