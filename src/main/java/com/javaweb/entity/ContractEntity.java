package com.javaweb.entity;

import com.javaweb.enums.ContractStatus;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;

@Entity
@Table(name = "contract")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ---------------- BÊN A (BÊN BÁN / CHO THUÊ) ----------------
    @Column(name = "seller_name")
    private String sellerName;

    @Column(name = "seller_id_number")
    private String sellerIdNumber;

    @Column(name = "seller_issue_date")
    private LocalDate sellerIssueDate;

    @Column(name = "seller_issue_place")
    private String sellerIssuePlace;

    @Column(name = "seller_phone")
    private String sellerPhone;

    @Column(name = "seller_address")
    private String sellerAddress;

    // ---------------- BÊN B (BÊN MUA / THUÊ) ----------------
    @Column(name = "buyer_name")
    private String buyerName;

    @Column(name = "buyer_id_number")
    private String buyerIdNumber;

    @Column(name = "buyer_issue_date")
    private LocalDate buyerIssueDate;

    @Column(name = "buyer_issue_place")
    private String buyerIssuePlace;

    @Column(name = "buyer_address")
    private String buyerAddress;

    @Column(name = "buyer_phone")
    private String buyerPhone;



    // ---------------- THÔNG TIN BẤT ĐỘNG SẢN ----------------
    @Column(name = "property_type")
    private String propertyType;

    @Column(name = "property_address")
    private String propertyAddress;

    @Column(name = "property_area")
    private BigDecimal propertyArea;

    @Column(name = "property_rooms")
    private String propertyRooms;

    @Column(name = "property_certificate_number")
    private String propertyCertificateNumber;

    @Column(name = "property_certificate_date")
    private LocalDate propertyCertificateDate;

    @Column(name = "property_certificate_place")
    private String propertyCertificatePlace;

    @Column(name = "rental_price")
    private BigDecimal rentalPrice;

    @Column(name = "total_price")
    private BigDecimal totalPrice;

    @Column(name = "rental_duration")
    private Integer rentalDuration;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    // ---------------- THÔNG TIN HỢP ĐỒNG ----------------
    @Column(name = "contract_code")
    private String contractCode;

    @Column(name = "contract_date")
    private LocalDate contractDate;

    @Column(name = "pdf_path")
    private String pdfPath;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "created_date")
    private Timestamp createdDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private ContractStatus status = ContractStatus.DRAFT;

    // ---------------- QUAN HỆ ----------------
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private CustomerEntity customer;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
}
