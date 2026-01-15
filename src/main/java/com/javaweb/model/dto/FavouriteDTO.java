package com.javaweb.model.dto;

import lombok.Data;

@Data
public class FavouriteDTO {
    private Long userId;
    private Long buildingId;

    // Getters & Setters
    public Long getUserId() { return userId; }
    public void setUserId(Long UserId) { this.userId = UserId; }

    public Long getBuildingId() { return buildingId; }
    public void setBuildingId(Long buildingId) { this.buildingId = buildingId; }
}
