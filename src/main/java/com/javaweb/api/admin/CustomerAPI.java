package com.javaweb.api.admin;

import com.javaweb.exception.MyException;
import com.javaweb.model.dto.*;
import com.javaweb.model.response.CustomerResponse;
import com.javaweb.model.response.ResponseDTO;
import com.javaweb.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Min;
import javax.validation.Valid;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/customers")
public class CustomerAPI {

    @Autowired
    private CustomerService customerService;

    @GetMapping
    public ResponseEntity<List<CustomerResponse>> getAllUsers(
            @RequestParam Map<String, Object> param,
            @RequestParam(required = false) String statuses) {

        System.out.println(">>> getAllUsers called với param = " + param);
        List<CustomerResponse> users = customerService.findAll(param, statuses);
        return ResponseEntity.ok(users);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable Long id) {
        CustomerDTO customer = customerService.getCustomerById(id);
        return ResponseEntity.ok(customer);
    }



    @PostMapping
    private ResponseEntity<?> createCustomer(@RequestBody CustomerDTO customerDTO , BindingResult bindingResult) throws MyException {
        try {
            if (bindingResult.hasErrors()) {
                List<String> errors = bindingResult.getFieldErrors().stream()
                        .map(FieldError::getDefaultMessage)
                        .collect(Collectors.toList());
                ResponseDTO responseDTO = new ResponseDTO();
                responseDTO.setMessage("Faild");
                responseDTO.setDetail(errors);
                return ResponseEntity.badRequest().body(responseDTO);
            }
            customerService.postAll(customerDTO);
            return ResponseEntity.ok("Created customer successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            throw e; // 👈 Đừng nuốt exception!
        }
    }

    @PostMapping("/nologin")
    private ResponseEntity<?> createCustomers(@RequestBody @Valid CustomerDTO customerDTO, BindingResult bindingResult) {
        ResponseDTO responseDTO = new ResponseDTO();

        try {
            if (bindingResult.hasErrors()) {
                List<String> errors = bindingResult.getFieldErrors()
                        .stream()
                        .map(FieldError::getDefaultMessage)
                        .collect(Collectors.toList());

                responseDTO.setMessage("Validation failed");
                responseDTO.setDetail(errors);
                return ResponseEntity.badRequest().body(responseDTO);
            }

            // Không đẩy lên Pagram
            customerService.postAlls(customerDTO, false); // Tham số `false` để không đẩy
            responseDTO.setMessage("Success");
            responseDTO.setDetail(Collections.singletonList("Customer created but not pushed to Pagram."));
        } catch (Exception e) {
            responseDTO.setMessage("Error");
            responseDTO.setDetail(Collections.singletonList(e.getMessage()));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDTO);
        }

        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/{ids}")
    private void deleteCustomer(@PathVariable Long[] ids){
        customerService.deleteAll(ids);
    }
    @PutMapping
    private void UpdateBuilding(@RequestBody CustomerDTO customerDTO){
        customerService.putAll(customerDTO);
    }


    @GetMapping("/{ids}")
    private Object loadStaff(@PathVariable Long ids){
        return customerService.loadStaff(ids);
    }

    @PutMapping("/staffs")
    private Object updateStaff(@RequestBody AssigmentCustomerDTO assigmentCustomerDTO){

        return customerService.saveStaff(assigmentCustomerDTO);
    }
}
