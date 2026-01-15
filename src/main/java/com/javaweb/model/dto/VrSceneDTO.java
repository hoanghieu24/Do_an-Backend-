package com.javaweb.model.dto;

import com.javaweb.entity.BuildingEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class VrSceneDTO {
    private Long id;
    private String name;
    private String imageUrl;
    private Float ath;
    private Float atv;
    private String targetScene;
    private String building;
}

