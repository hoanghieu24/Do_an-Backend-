package com.javaweb.model.dto;

import com.javaweb.entity.RentareaEntity;
import com.javaweb.enums.buildingType;
import com.javaweb.enums.districtCode;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Min;
import javax.validation.Valid;

import javax.validation.Valid;

import java.util.List;



@Getter
@Setter
public class BuildingDTO extends AbstractDTO {

    @NotBlank(message = "Tên Toà nhà không được để rỗng !!")
    private String name;
    private String ward;
    private String street;
    private String rentareaEntity_List;
    private List<buildingType> type;
    private Integer numberOfBasement;
    private districtCode district;
    @Min(value = 0 , message = "Toa nha Khong duoc am ")
    private Long  rentPrice;
    private Long floorArea;
    private String level;
    private String overTimeFee;
    private String electricityFee;
    private String deposit;
    private String payment;
    private String rentTime;
    private String decorationTime;
    private String rentPriceDescription;
    private String carFee;
    private String motoFee;
    private String strucTure;
    private String direction;
    private String note;
    private String managername;
    private String managerPhoneNumber;
    private String serviceFee;
    private double brokerageFee;
    private String image;
    private String imageBase64;
    private String imageName;
    public String getImageBase64() {
        if (imageBase64 != null) {
            return imageBase64.split(",")[1];
        }
        return null;
    }
}
