package com.javaweb.repository.custom;

import com.javaweb.builder.TransationSearchBuilder;
import com.javaweb.entity.TransactionEntity;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TransactionRepositoryCustom {
    List<TransactionEntity> findAll(TransationSearchBuilder transationSearchBuilder, Long id);
    List<TransactionEntity> findAlls(TransationSearchBuilder transationSearchBuilder, Long id);
    int countTotalItem();
    List<TransactionEntity> getAllCustomer(Pageable pageable);
}
