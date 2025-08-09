package com.javaweb.model.dto;


import com.javaweb.entity.CustomerEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TransactionDTO extends AbstractDTO{

    private Long id;
    private String fullName;
    private String phone;
    private String email;
    private String note;
    private String status;
    private String companyName;
    private List<String> Code;
    private Long CustomerId;
}
