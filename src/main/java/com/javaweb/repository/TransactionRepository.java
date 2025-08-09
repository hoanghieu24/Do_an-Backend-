package com.javaweb.repository;

import com.javaweb.entity.CustomerEntity;
import com.javaweb.entity.TransactionEntity;
import com.javaweb.model.dto.TransactionDTO;
import com.javaweb.repository.custom.TransactionRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> , TransactionRepositoryCustom {
    List<TransactionEntity> deleteAllByCustomerIn(List<CustomerEntity> customerEntities);
    @Query(value = "SELECT * FROM transaction WHERE code = :code AND customerid = :id", nativeQuery = true)
    List<TransactionEntity> findActiveCustomers(String code , Long id );
    @Query("SELECT t FROM TransactionEntity t WHERE t.customer.id = :customerId")
    List<TransactionEntity> findTransactionsByCustomerId(@Param("customerId") Long customerId);
    TransactionDTO getTransactionById(Long id);
    @Query("SELECT t FROM TransactionEntity t WHERE t.id = :id")
    List<TransactionEntity> findByTransactionId(@Param("id") Long id);
    TransactionDTO getCustomerById(Long id);
    @Query("SELECT t FROM TransactionEntity t JOIN t.customer c WHERE c.id = :id")
    List<TransactionEntity> findByTranId(@Param("id") Long id);


}
