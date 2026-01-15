package com.javaweb.model.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
public class AssigmentCustomerDTO {
    private Long customerId;
    private List<Long> staffs;
}
