package com.javaweb.model.response;

import com.javaweb.entity.CustomerEntity;
import com.javaweb.entity.TransactionEntity;
import com.javaweb.model.dto.AbstractDTO;
import lombok.*;

import javax.persistence.PrePersist;
import java.util.Date;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransactionResponse extends AbstractDTO  {
    private Long id;
    private Long customerId;
    private String code;
    private Date createdDate;
    private String createdBy;
    private Date modifiedDate;
    private String modifiedBy;
    private String note;
    private Long staffId;

    @PrePersist
    public void prePersist() {
        this.modifiedBy = null;
        this.modifiedDate = null;
    }
}
