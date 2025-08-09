package com.javaweb.entity;

import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.*;
@Entity
@Table(name = "customer")
@Getter
@Setter

public class CustomerEntity extends BaseEntity {

    @Column(name = "fullname")
    private String fullName;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;

    @Column(name = "status")
    private String status;

    @Column(name = "companyname")
    private String companyName;

    @Column(name = "demand")
    private String demand;

    @Column(name = "is_active")
    private Integer isActive;

    @ManyToMany(mappedBy = "customerEntities", fetch = FetchType.LAZY)
    private List<BuildingEntity> buildingEntities = new ArrayList<>();

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY , cascade = {CascadeType.PERSIST,CascadeType.MERGE} , orphanRemoval = true)
    private List<TransactionEntity> transactions;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "assignmentcustomer",
            joinColumns = @JoinColumn(name = "customerid", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "staffid", nullable = false))
    private List<CustomerEntity> customerEntities = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "assignmentcustomer",
            joinColumns = @JoinColumn(name = "customerid", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "staffid", nullable = false))
    private List<UserEntity> users = new ArrayList<>();

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FavouriteEntity> favourites = new ArrayList<>();


//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinTable(name = "signthecontract",
//            joinColumns = @JoinColumn(name = "customerid", nullable = false),
//            inverseJoinColumns = @JoinColumn(name = "customers", nullable = false))
//    private SignTheContractEntity customerid;


}
