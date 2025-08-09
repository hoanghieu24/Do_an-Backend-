package com.javaweb.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AssigmentCustomerDTO {
    private Long customerId;
    private List<Long> staffs;
}
