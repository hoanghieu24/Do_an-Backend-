package com.javaweb.model.response;

import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
public class BuildingResponse {
    private Long Id;
    private String nameBuilding;
    private String Address;
    private Integer numberOfBasement;
    private String managername;
    private String managerphone;
    private Integer floorarea;
    private Long empty_area;
    private String rental_area;
    private Long rentPrice;
    private Long Servicefee;
    private Double Brokerage_fee;
    private List<Long> staffid;
    private String image;
    private String type;
    private String overTimeFee;
    private String electricityFee;
    private String deposit;
    private String payment;
    private String rentTime;
    private String decorationTime;
    private String rentPriceDescription;
    private String carfee;
    private String motofee;
    private String strucTure;
    private String direction;
    private String note;
    private String formattedRentPrice;
}