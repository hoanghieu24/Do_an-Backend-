package com.javaweb.model.request;

import com.javaweb.enums.Status;
import com.javaweb.enums.TransactionType;
import com.javaweb.model.dto.AbstractDTO;
import lombok.*;

import java.util.List;

@Getter
@Setter
public class CustomerSearchRequest extends AbstractDTO {
        private String fullname;
        private String email;
        private String phone;
        private String status;
        private Long staffId;
        private TransactionType CSKH;
        private TransactionType DDX;
}
