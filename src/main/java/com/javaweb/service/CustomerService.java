package com.javaweb.service;

import com.javaweb.entity.TransactionEntity;
import com.javaweb.enums.Status;
import com.javaweb.exception.MyException;
import com.javaweb.model.dto.AssigmentCustomerDTO;
import com.javaweb.model.dto.CustomerDTO;
import com.javaweb.model.dto.TransactionDTO;
import com.javaweb.model.response.CustomerResponse;
import com.javaweb.model.response.TransactionResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface CustomerService  {
    void postAll(CustomerDTO customerDTO) throws MyException;
    void postAlls(CustomerDTO customerDTO, boolean check) throws MyException;


    List<CustomerResponse> findAll(Map<String, Object> param, String status);
    List<TransactionResponse> findAllDDX(String code , Long id );
    List<TransactionResponse> findAllCSKH(String code , Long id );
    List<TransactionEntity> findTransactionsByCustomerId(Long customerId);

    List<CustomerDTO> getUser(String searchValue, Pageable pageable);
    void deleteAll(Long[] ids);
    int countTotalItems();
    CustomerDTO getCustomerById(Long id);

    void putAll(CustomerDTO customerDTO);
    Object loadStaff(Long ids);
    Object saveStaff(AssigmentCustomerDTO assigmentCustomerDTO);


}
