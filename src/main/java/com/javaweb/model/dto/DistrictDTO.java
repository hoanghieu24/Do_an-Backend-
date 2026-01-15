package com.javaweb.model.dto;

import lombok.Data;

@Data
public class DistrictDTO {
    private Long[] Buildingids;

    public Long[] getBuildingids() {
        return Buildingids;
    }

    public void setBuildingids(Long[] buildingids) {
        Buildingids = buildingids;
    }
}
