package com.javaweb.entity;

import lombok.*;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "assignmentcustomer")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AssignmentCustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    @JoinColumn(name = "staffid", nullable = false)
    private UserEntity staff; // tương ứng user

    @ManyToOne
    @JoinColumn(name = "customerid", nullable = false)
    private CustomerEntity customer;


    @Column(name = "createddate")
    private LocalDateTime createdDate;

    @Column(name = "modifieddate")
    private LocalDateTime modifiedDate;

    @Column(name = "createdby")
    private String createdBy;

    @Column(name = "modifiedby")
    private String modifiedBy;
}
