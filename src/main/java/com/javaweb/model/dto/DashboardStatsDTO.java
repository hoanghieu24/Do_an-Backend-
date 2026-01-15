package com.javaweb.model.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DashboardStatsDTO {
    private long totalCustomers;
    private long totalBuildings;
    private long activeContracts;
    private double monthlyRevenue;
    private String latestBuildingAddress; // <— thêm field mới
}

