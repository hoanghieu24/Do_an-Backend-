package com.javaweb.service.impl;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.builder.TransationSearchBuilder;
import com.javaweb.converter.TransactionConverter;
import com.javaweb.converter.TransactionSearchBuilderConverter;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.TransactionEntity;
import com.javaweb.model.dto.TransactionDTO;
import com.javaweb.model.response.BuildingResponse;
import com.javaweb.model.response.TransactionResponse;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.CustomerRepository;
import com.javaweb.repository.TransactionRepository;
import com.javaweb.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Transactional
@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private TransactionConverter transactionConverter;
    @Autowired
    private TransactionSearchBuilderConverter toBuildingSearchBuilder;

    @Override
    public void postNote(TransactionDTO transactionDTO) {

        List<TransactionEntity> transactionEntities = transactionRepository.findTransactionsByCustomerId(transactionDTO.getCustomerId());
        if (!transactionEntities.isEmpty()) {
            for (TransactionEntity transactionEntity : transactionEntities) {
                TransactionEntity newTransactionEntity = new TransactionEntity();
                newTransactionEntity.setCustomer(customerRepository.findById(transactionDTO.getCustomerId()).orElse(null));
                String codeWithoutBrackets = transactionDTO.getCode().toString().replaceAll("[\\[\\]]", "");
                newTransactionEntity.setCode(codeWithoutBrackets);
                newTransactionEntity.setNote(transactionDTO.getNote());
                transactionRepository.save(newTransactionEntity);
                break;
            }
        } else {
            System.out.println("Không tồn tại TransactionEntity với ID: " + transactionDTO.getId());
        }
    }

    @Override
    public List<TransactionResponse> findAllCSKH(Map<String, Object> param, List<String> Code , Long id) {
//        List<TransactionEntity> transactionEntities = transactionRepository.findActiveCustomers(code,id);
        TransationSearchBuilder transationSearchBuilder = toBuildingSearchBuilder.toTransationSearchBuilder(param, Collections.singletonList("CSKH"));
        List<TransactionEntity> transactionEntities = transactionRepository.findAll(transationSearchBuilder,id);
        if (transationSearchBuilder.getCustomerId() != null) {
            System.out.println("Bi null roi");
        }

        List<TransactionResponse> transactionResponses = new ArrayList<>();
        for (TransactionEntity be : transactionEntities) {
            transactionResponses.add(transactionConverter.convertoResponse(be));

        }
        return transactionResponses;
    }

    @Override
    public List<TransactionResponse> findAllDDX(Map<String, Object> param, List<String> Code, Long id) {
        TransationSearchBuilder transationSearchBuilder = toBuildingSearchBuilder.toTransationSearchBuilder(param, Collections.singletonList("DDX"));
        List<TransactionEntity> transactionEntities = transactionRepository.findAlls(transationSearchBuilder,id);
        if (transationSearchBuilder.getCustomerId() != null) {
            System.out.println("Bi null roi");
        }

        List<TransactionResponse> transactionResponses = new ArrayList<>();
        for (TransactionEntity be : transactionEntities) {
            transactionResponses.add(transactionConverter.convertoResponse(be));

        }
        return transactionResponses;
    }

    @Override
    public void putAllNote(TransactionDTO transactionDTO) {
        List<TransactionEntity> transactionEntities = transactionRepository.findByTransactionId(transactionDTO.getId());
        for (TransactionEntity transactionEntity : transactionEntities) {
            if (transactionDTO.getCode() != null && !transactionDTO.getCode().isEmpty()) {
                String code = transactionDTO.getCode().get(0);
                transactionEntity.setCode(code);
            }
            transactionEntity.setNote(transactionDTO.getNote());
            transactionEntity.setCustomer(customerRepository.findById(transactionDTO.getCustomerId()).orElse(null));
            transactionRepository.save(transactionEntity);
        }
    }


    @Override
    public TransactionDTO getTranById(Long id) {
        List<TransactionEntity> transactionEntity = transactionRepository.findTransactionsByCustomerId(id);
        return transactionConverter.convertToDto(transactionEntity);
    }


}
