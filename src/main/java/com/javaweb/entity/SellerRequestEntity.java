package com.javaweb.entity;

import lombok.*;

import javax.persistence.*;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "seller_request")
@Getter
@Setter
public class SellerRequestEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="user_id")
    private UserEntity user;

    private String fullName;
    private String userName;

    @Column(name = "phone")
    private String phoneNumber;

    private String email;

    private String status; // PENDING, APPROVED, REJECTED
    private String note;

    private LocalDateTime createdDate;
    private LocalDateTime approvedDate;

    @ManyToOne
    @JoinColumn(name="approved_by")
    private UserEntity approvedBy;
}

