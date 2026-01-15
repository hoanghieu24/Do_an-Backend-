package com.javaweb.model.dto;

import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContractDTO {

    private Long id;

    private String sellerName;
    private String sellerIdNumber;
    private LocalDate sellerIssueDate;
    private String sellerIssuePlace;
    private String sellerAddress;
    private String sellerPhone;

    private String buyerName;
    private String buyerIdNumber;
    private LocalDate buyerIssueDate;
    private String buyerIssuePlace;
    private String buyerAddress;
    private String buyerPhone;

    private String propertyType;
    private String propertyAddress;
    private BigDecimal propertyArea;
    private String propertyRooms;
    private String propertyCertificateNumber;
    private LocalDate propertyCertificateDate;
    private String propertyCertificatePlace;
    private BigDecimal rentalPrice;
    private BigDecimal totalPrice;
    private Integer rentalDuration;
    private LocalDate startDate;
    private LocalDate endDate;


    private String contractCode;
    private LocalDate contractDate;
    private String pdfPath;
    private String description;
    private Long customerId;
    private Long userId;
    private String status;
}
