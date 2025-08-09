package com.javaweb.service;

import com.javaweb.model.dto.TransactionDTO;
import com.javaweb.model.response.BuildingResponse;
import com.javaweb.model.response.TransactionResponse;

import java.util.List;
import java.util.Map;

public interface TransactionService {
    void postNote(TransactionDTO transactionDTO);
    List<TransactionResponse> findAllCSKH(Map<String, Object> param, List<String> Code , Long id );
    List<TransactionResponse> findAllDDX(Map<String, Object> param, List<String> Code , Long id);
    void putAllNote(TransactionDTO transactionDTO);
    TransactionDTO  getTranById(Long id);
}
