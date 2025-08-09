package com.javaweb.converter;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.CustomerEntity;
import com.javaweb.entity.TransactionEntity;
import com.javaweb.enums.districtCode;
import com.javaweb.model.dto.CustomerDTO;
import com.javaweb.model.dto.TransactionDTO;
import com.javaweb.model.response.BuildingResponse;
import com.javaweb.model.response.TransactionResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TransactionConverter {
    @Autowired
    private ModelMapper modelMapper;

    public TransactionResponse convertoResponse(TransactionEntity te) {
        TransactionResponse transactionResponse = modelMapper.map(te, TransactionResponse.class);

        return transactionResponse;
    }

    public TransactionDTO convertToDto (List<TransactionEntity> entity){
        TransactionDTO result = modelMapper.map(entity, TransactionDTO.class);
        return result;
    }
}
