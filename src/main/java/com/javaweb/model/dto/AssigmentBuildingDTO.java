package com.javaweb.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class AssigmentBuildingDTO {
    private Long buildingId;
    private List<Long> staffs;
}
