package com.javaweb.api.admin;

import com.javaweb.entity.ContractEntity;
import com.javaweb.model.dto.ContractDTO;
import com.javaweb.model.response.CustomerResponse;
import com.javaweb.repository.ContractRepository;
import com.javaweb.service.IContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/contracts")
public class ContractAPI {

    @Autowired
    private IContractService contractService;

    @Autowired
    private ContractRepository contractRepository;

    @PostMapping
    public ResponseEntity<String> createContract(@RequestBody ContractDTO contractDTO) {
        try {
            contractService.saveContract(contractDTO);
            return ResponseEntity.ok("Tạo hợp đồng thành công và đã lưu file PDF!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Lỗi khi tạo hợp đồng: " + e.getMessage());
        }
    }
//    @GetMapping
//    public ResponseEntity<List<ContractEntity>> getContractsAll(
//        @RequestParam Map<String, Object> param,
//        @RequestParam(required = false) String statuses) {
//
//            System.out.println(">>> getAllUsers called với param = " + param);
//            List<CustomerResponse> users = customerService.findAll(param, statuses);
//            return ResponseEntity.ok(users);
//    }


    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<ContractDTO>> getContractsByCustomerId(@PathVariable Long customerId) {
        List<ContractDTO> contracts = contractService.getContractsByCustomerId(customerId);
        return ResponseEntity.ok(contracts);
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<byte[]> downloadContractPdf(@PathVariable Long id) {
        byte[] pdfData = contractService.getContractPdfById(id);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=contract_" + id + ".pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdfData);
    }
    @DeleteMapping("/{ids}")
    public void deleteContract(@PathVariable Long[] ids) {
        contractService.deleteAll(ids);
    }
    @GetMapping("/view/{id}")
    public ResponseEntity<byte[]> viewContractPdf(@PathVariable Long id) {
        byte[] pdfData = contractService.getContractPdfById(id);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=contract_" + id + ".pdf")
                .header("X-Frame-Options", "SAMEORIGIN")  // ✅ Cho phép nhúng nếu cùng domain
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdfData);
    }

    @PutMapping()
    private void updateContract(@RequestBody ContractDTO contractDTO) {
        contractService.putAll(contractDTO);
    }

    @GetMapping
    public List<ContractDTO> getAllContracts() {
        return contractService.getAllContracts();
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<?> updateContractStatus(@PathVariable Long id, @RequestBody Map<String, String> body) {
        String status = body.get("status");
        contractService.updateStatus(id, status);
        return ResponseEntity.ok().build();
    }




}
