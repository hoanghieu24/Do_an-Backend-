package com.javaweb.converter;

import com.javaweb.entity.*;
import com.javaweb.enums.districtCode;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.dto.CustomerDTO;
import com.javaweb.model.dto.UserDTO;
import com.javaweb.model.response.BuildingResponse;
import com.javaweb.model.response.CustomerResponse;
import com.javaweb.repository.CustomerRepository;
import com.javaweb.repository.TransactionRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CustomerConverter {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private TransactionRepository transactionRepository;



    public CustomerResponse convertoResponse(CustomerEntity be) {
        CustomerResponse customerResponse = modelMapper.map(be, CustomerResponse.class);

        return customerResponse;
    }
    public CustomerDTO convertToDto(CustomerEntity entity) {
        CustomerDTO result = modelMapper.map(entity, CustomerDTO.class);
        // Map danh sách transactionIds từ transactions
        if (entity.getTransactions() != null) {
            result.setTransactionIds(
                    entity.getTransactions().stream()
                            .map(TransactionEntity::getId) // Lấy ID của mỗi transaction
                            .collect(Collectors.toList())
            );
        }
        return result;
    }


    public CustomerEntity convertToEntity (CustomerDTO dto){
        CustomerEntity result = modelMapper.map(dto, CustomerEntity.class);
        return result;
    }
    public List<TransactionEntity> convertPostAndEditCustomer(CustomerDTO customerDTO, CustomerEntity customerEntity) {
        List<TransactionEntity> transactionEntities = new ArrayList<>();
        String TransactionInput = customerDTO.getDemand();
            TransactionEntity transactionEntity = new TransactionEntity();

        System.out.println("note 2a : " + TransactionInput);
                transactionEntity.setCustomer(customerEntity);
                transactionEntities.add(transactionEntity);
                transactionRepository.save(transactionEntity);
                return transactionEntities;
    }
}
