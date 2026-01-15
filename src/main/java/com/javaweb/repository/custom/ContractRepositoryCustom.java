package com.javaweb.repository.custom;

import com.javaweb.builder.CustomerSearchBuilder;
import com.javaweb.entity.ContractEntity;
import com.javaweb.model.dto.ContractDTO;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ContractRepositoryCustom {
    List<ContractEntity> findByStaffId(Long staffId, ContractDTO builder);
}

