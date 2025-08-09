package com.javaweb.model.response;

import com.javaweb.enums.Status;
import com.javaweb.model.dto.AbstractDTO;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerResponse extends AbstractDTO {
    private Long id;
    private String fullName;
    private String email;
    private String phone;
    private String status;
    private String staffId;
    private String demand;
    private Date createdDate;
    private String createdBy;
    private Integer is_Active;
}
