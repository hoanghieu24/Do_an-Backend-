package com.javaweb.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "transaction")
@Getter
@Setter
public class TransactionEntity extends BaseEntity {

    @Column(name = "code")
    private String code;

    @Column(name = "note")
    private String note;

    @Column(name = "staffid")
    private Long  staffid;



    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customerid")
    private CustomerEntity customer;


}

