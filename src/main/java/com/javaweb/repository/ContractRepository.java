package com.javaweb.repository;

import com.javaweb.entity.ContractEntity;
import com.javaweb.entity.CustomerEntity;
import com.javaweb.repository.custom.ContractRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContractRepository extends JpaRepository<ContractEntity, Long> , ContractRepositoryCustom {



    @Query("SELECT c.pdfPath FROM ContractEntity c WHERE c.id = :id")
    String getContractPdfPathById(@Param("id") Long id);

    @Query("SELECT c FROM ContractEntity c WHERE c.customer.id = :customerId")
    List<ContractEntity> findAllByCustomerId(@Param("customerId") Long customerId);

    @Query("""
    SELECT COUNT(c)
    FROM ContractEntity c
    WHERE c.status = 'ACTIVE'
    AND (:isAdmin = TRUE OR c.customer IN (
        SELECT ac.customer
        FROM AssignmentCustomerEntity ac
        WHERE ac.staff.id = :staffId
    ))
""")
    long countActiveContracts(@Param("staffId") Long staffId, @Param("isAdmin") boolean isAdmin);

    @Query("""
    SELECT SUM(c.totalPrice) 
    FROM ContractEntity c
    WHERE c.status = 'COMPLETED'
    AND (:isAdmin = TRUE OR c.customer IN (
        SELECT ac.customer
        FROM AssignmentCustomerEntity ac
        WHERE ac.staff.id = :staffId
    ))
""")
    Long getMonthlyRevenue(@Param("staffId") Long staffId, @Param("isAdmin") boolean isAdmin);



    List<ContractEntity> findByIdIn(Long[] ids);


    Object countByContractCode(String active);
    @Query("""
  SELECT c FROM ContractEntity c
  WHERE c.status = 'PENDING'
""")
    List<ContractEntity> findPendingContracts();


}
