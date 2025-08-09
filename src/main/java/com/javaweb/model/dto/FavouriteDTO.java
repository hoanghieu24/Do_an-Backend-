package com.javaweb.model.dto;

public class FavouriteDTO {
    private Long customerId;
    private Long buildingId;

    // Getters & Setters
    public Long getCustomerId() { return customerId; }
    public void setCustomerId(Long customerId) { this.customerId = customerId; }

    public Long getBuildingId() { return buildingId; }
    public void setBuildingId(Long buildingId) { this.buildingId = buildingId; }
}
