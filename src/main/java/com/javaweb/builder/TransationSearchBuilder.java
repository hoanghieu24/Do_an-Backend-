package com.javaweb.builder;

import lombok.*;

import java.util.Date;


@Getter
@Setter
public class TransationSearchBuilder {
    private String code ;
    private String note ;
    private Long customerId;
    private Date modifiedBy;
    private Date modifiedDate;
    private Date createDate ;
    private Date createBy;
    private String staffId ;

    private TransationSearchBuilder(Builder builder) {
        this.code = builder.code;
        this.note = builder.note;
        this.customerId = builder.customerId;
        this.modifiedBy = builder.modifiedBy;
        this.modifiedDate = builder.modifiedDate;
        this.createDate = builder.createDate;
        this.createBy = builder.createBy;
        this.staffId = builder.staffId;

    }

    public static class Builder {
        private String code ;
        private String note ;
        private Long customerId;
        private Date modifiedBy;
        private Date modifiedDate;
        private Date createDate ;
        private Date createBy;
        private String staffId ;
        public Builder code(String code) {
            this.code = code;
            return this;
        }
        public Builder note(String note) {
            this.note = note;
            return this;
        }
        public Builder setCustomerId(Long customerId) {
            this.customerId = customerId;
            return this;
        }
        public Builder setCreateDate(Date createDate) {
            this.createDate = createDate;
            return this;
        }
        public Builder setModifiedDate(Date modifiedDate) {
            this.modifiedDate = modifiedDate;
            return this;
        }
        public Builder setModifiedBy(Date modifiedBy) {
            this.modifiedBy = modifiedBy;
            return this;
        }
        public Builder setModifyDate(Date modifyDate) {
            this.modifiedDate = modifyDate;
            return this;
        }
        public Builder setStaffId(String staffId) {
            this.staffId = staffId;
            return this;
        }
        public TransationSearchBuilder build() {
            return new TransationSearchBuilder(this);
        }
    }

}
