package com.javaweb.builder;

import lombok.*;
import org.springframework.stereotype.Component;

@Getter
@Setter
public class CustomerSearchBuilder {
    private String fullName;
    private String email;
    private String phone;
    private String status;
    private Integer staffId;
    private String note;
    private String createdBy;

    private CustomerSearchBuilder(Builder builder) {
        this.fullName = builder.fullName;
        this.email = builder.email;
        this.phone = builder.phone;
        this.status = builder.status;
        this.staffId = builder.staffId;
        this.note = builder.note;
        this.createdBy = builder.createdBy;
    }

    public  static class Builder {
        private String fullName;
        private String email;
        private String phone;
        private String status;
        private Integer staffId;
        private String note;
        private String createdBy;
        public Builder setFullName(String fullName) {
            this.fullName = fullName;
            return this;
        }
        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }
        public Builder setPhone(String phone) {
            this.phone = phone;
            return this;
        }
        public Builder setStatus(String status) {
            this.status = status;
            return this;
        }
        public Builder setStaffId(Integer staffId) {
            this.staffId = staffId;
            return this;
        }
        public Builder setNote(String note) {
            this.note = note;
            return this;
        }
        public Builder setCreatedBy(String createdBy) {
            this.createdBy = createdBy;
            return this;
        }
        public CustomerSearchBuilder build() {
            return new CustomerSearchBuilder(this);
        }
    }

}
