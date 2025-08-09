package com.javaweb.builder;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@Builder
public class BuildingSearchBuilder {
    private String name ;
    private String floorarea ;
    private String ward ;
    private String street ;
    private String district ;
    private Integer numberOfBasement ;
    private List<String> typeCode = new ArrayList<>() ;
    private String managername ;
    private String managerphone ;
    private Long rentPriceFrom ;
    private Long rentPriceTo ;
    private Integer areaFrom ;
    private Integer areaTo ;
    private Integer staffId ;
    private String direction;
    private String servicefee;
    private String deposit;
    private String renttime;
    private String rentpricedescription;
    private String note;

}
