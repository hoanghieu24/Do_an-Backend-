package com.javaweb.model.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class ProductDto {
    private Long id;
    private String name;
    private String description;
    private String image;
    private String location;
    private String type;
    private Double price;
}
