package com.javaweb.entity;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "building")
public class BuildingEntity extends BaseEntity {

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "ward")
    private String ward;

    @Column(name = "street")
    private String street;

    @Column(name = "structure")
    private String structure;

    @Column(name = "numberofbasement")
    private Integer numberOfBasement;

    @Column(name = "floorarea")
    private Long floorarea;

    @Column(name = "direction")
    private String direction;

    @Column(name = "level")
    private String level;

    @Column(name = "rentprice")
    private Long rentPrice;

    @Column(name = "rentpricedescription")
    private String rentpricedescription;

    @Column(name = "servicefee")
    private String servicefee;

    @Column(name = "carfee")
    private String carfee;

    @Column(name = "waterfee")
    private String waterfee;

    @Column(name = "electricityfee")
    private String electricityfee;

    @Column(name = "deposit")
    private String deposit;

    @Column(name = "payment")
    private String payment;

    @Column(name = "renttime")
    private String renttime;

    @Column(name = "decorationtime")
    private String decorationtime;

    @Column(name = "brokeragefee")
    private BigDecimal brokeragefee;  // Sử dụng BigDecimal thay vì Double

    @Column(name = "note")
    private String note;

    @Column(name = "linkofbuilding")
    private String linkofbuilding;

    @Column(name = "map")
    private String map;

    @Column(name = "avatar")
    private String image;  // Khớp với cột 'avatar' trong bảng SQL

    @Column(name = "managername")
    private String managername;

    @Column(name = "managerphone")
    private String managerPhoneNumber;  // Khớp với cột 'managerphone' trong bảng SQL

    @Column(name = "district")
    private String districtId;

    @Column(name = "type")
    private String type;



    @OneToMany(mappedBy = "building", fetch = FetchType.LAZY , cascade = {CascadeType.PERSIST,CascadeType.MERGE} , orphanRemoval = true)
    private List<RentareaEntity> rentareaEntity_List = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "assignmentbuilding",
            joinColumns = @JoinColumn(name = "buildingid", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "staffid", nullable = false))
    private List<UserEntity> users = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "assignmentcustomer",
            joinColumns = @JoinColumn(name = "customerid", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "staffid", nullable = false))
    private List<CustomerEntity> customerEntities = new ArrayList<>();


    @OneToMany(mappedBy = "building", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FavouriteEntity> favourites = new ArrayList<>();

}

