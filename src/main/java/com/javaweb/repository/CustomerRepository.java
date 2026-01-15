package com.javaweb.repository;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.builder.CustomerSearchBuilder;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.CustomerEntity;
import com.javaweb.entity.TransactionEntity;
import com.javaweb.repository.custom.CustomerRepositoryCustom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> , CustomerRepositoryCustom {
    List<CustomerEntity> findByTransactionsIn(List<TransactionEntity> transactions);
    List<CustomerEntity> findByIdIn(Long[] ids);
    Page<CustomerEntity> findByFullNameContainingIgnoreCaseAndIsActiveNot( String fullName, Integer isActive,
                                                                                                  Pageable pageable);
    Page<CustomerEntity> findByIsActiveNot( int isActive, Pageable pageable);

    @Query("SELECT c FROM CustomerEntity c WHERE c.isActive = 1 ")
    List<CustomerEntity> findActiveCustomers();

    CustomerEntity findByIdAndIsActive(Long id , Integer isActive);
    public Optional<CustomerEntity> findByPhone(String phone);
    boolean existsByPhone(String phone);
    @Query("SELECT c FROM CustomerEntity c WHERE c.modifiedDate >= :from")
    List<CustomerEntity> findNewCustomers(@Param("from") LocalDateTime from);

    @Query("""
        SELECT DISTINCT c
        FROM CustomerEntity c
        JOIN c.users u
        WHERE u.id = :staffId
    """)
    List<CustomerEntity> findByStaff(@Param("staffId") Long staffId);


}
