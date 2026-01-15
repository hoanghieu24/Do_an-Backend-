package com.javaweb.model.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Data
public class AssigmentBuildingDTO {
    private Long buildingId;
    private List<Long> staffs;
}
