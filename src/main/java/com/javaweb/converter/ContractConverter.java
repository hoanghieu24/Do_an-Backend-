package com.javaweb.converter;

import com.javaweb.entity.ContractEntity;
import com.javaweb.model.dto.ContractDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ContractConverter {

    private final ModelMapper mapper = new ModelMapper();

    public ContractEntity toEntity(ContractDTO dto) {
        return mapper.map(dto, ContractEntity.class);
    }

    public ContractDTO toDTO(ContractEntity entity) {
        return mapper.map(entity, ContractDTO.class);
    }
}
