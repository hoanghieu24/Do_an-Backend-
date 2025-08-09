package com.javaweb.repository.custom;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.builder.CustomerSearchBuilder;
import com.javaweb.converter.BuildingSearchBuilderConverter;
import com.javaweb.entity.CustomerEntity;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CustomerRepositoryCustom {
    int countTotalItem();
    List<CustomerEntity> getAllCustomer(Pageable pageable);
//    public List<CustomerEntity> findAll(BuildingSearchBuilder customerEntity);
public List<CustomerEntity> findAll(CustomerSearchBuilder customerSearchBuilder);
}
