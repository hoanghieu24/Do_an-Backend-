package com.javaweb.api.admin;

import com.javaweb.model.dto.*;
import com.javaweb.model.response.ResponseDTO;
import com.javaweb.service.CustomerService;
import com.javaweb.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/transaction")
public class TransactionAPI {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private TransactionService transactionService;


    @PostMapping("/note")
    private void CreateNote(@RequestBody TransactionDTO transactionDTO){
        transactionService.postNote(transactionDTO);
    }
    @PutMapping("/note/edit")
    public ResponseEntity<String> updateNote(@RequestBody TransactionDTO transactionDTO) {
        if (transactionDTO.getId() == null) {
            return ResponseEntity.badRequest().body("ID không được phép null!");
        }

        transactionService.putAllNote(transactionDTO);
        return ResponseEntity.ok("Cập nhật ghi chú thành công");
    }


}
