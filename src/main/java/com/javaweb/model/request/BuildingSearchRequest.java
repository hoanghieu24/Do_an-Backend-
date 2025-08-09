package com.javaweb.model.request;

import com.javaweb.model.dto.AbstractDTO;
import lombok.Getter;
import lombok.Setter;


import  java.util.*;
//
@Getter
@Setter
public class BuildingSearchRequest extends AbstractDTO {
    private String name;
    private String floorarea;
    private String district;
    private String ward;
    private String street;
    private Integer numberofbasement;
    private String direction;
    private Long lever;
    private Long areaFrom;
    private Long areaTo;
    private Long rentPriceFrom;
    private Long rentPriceTo;
    private String managername;
    private String managerphone;
    private Long staffId;
    private List<String> typeCode;


}