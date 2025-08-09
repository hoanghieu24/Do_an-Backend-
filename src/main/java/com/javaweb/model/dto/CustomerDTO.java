package com.javaweb.model.dto;


import com.javaweb.entity.TransactionEntity;
import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Min;
import javax.validation.Valid;

import java.util.List;

@Getter
@Setter
public class CustomerDTO extends AbstractDTO{
    @NotBlank(message = "fullname can not be blank !! ")
    private String fullName;
    @NotBlank(message = "phone can not be blank !! ")

    private String phone;
    private String email;
    private String demand;
    private String status;
    private String companyName;
    private List<Long> transactionIds;


}
