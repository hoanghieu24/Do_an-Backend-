package com.javaweb.service;

import com.javaweb.model.dto.ContractDTO;

import java.util.List;

public interface IContractService {
    void saveContract(ContractDTO contractDTO) throws Exception;
    List<ContractDTO> getContractsByCustomerId(Long id);

    byte[] getContractPdfById(Long id);
    void putAll(ContractDTO contractDTO);
    List<ContractDTO> getAllContracts();
    void deleteAll(Long[] ids);
    void updateStatus(Long id, String status);

}
