package com.javaweb.service.impl;


import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.builder.CustomerSearchBuilder;
import com.javaweb.builder.TransationSearchBuilder;
import com.javaweb.converter.*;
import com.javaweb.entity.*;
import com.javaweb.enums.Status;
import com.javaweb.exception.MyException;
import com.javaweb.model.dto.AssigmentCustomerDTO;
import com.javaweb.model.dto.CustomerDTO;
import com.javaweb.model.dto.TransactionDTO;
import com.javaweb.model.response.CustomerResponse;
import com.javaweb.model.response.ResponseDTO;
import com.javaweb.model.response.StaffResponseDto;
import com.javaweb.model.response.TransactionResponse;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.CustomerRepository;
import com.javaweb.repository.TransactionRepository;
import com.javaweb.repository.UserRepository;
import com.javaweb.service.CustomerService;
import org.apache.commons.lang.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.*;
import java.util.stream.Collectors;

@Transactional
@Service
public class CustomerServiceimpl implements CustomerService {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CustomerConverter customerConverter;
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BuildingSearchBuilderConverter buildingSearchBuilderConverter;
    @Autowired
    private BuildingRepository buildingRepository;
    @Autowired
    private TransactionConverter transactionConverter;
//    @Autowired
//    private CustomerService customerService;
    @Autowired
    private CustomerSearchBuilderConveter customerSearchBuilderConveter;

    private TransactionSearchBuilderConverter transactionSearchBuilderConverter;



    @Override
    public void postAll(CustomerDTO customerDTO) throws MyException {

        CustomerEntity customerEntity = modelMapper.map(customerDTO, CustomerEntity.class);
        Status status = Status.valueOf(String.valueOf(customerDTO.getStatus()));
//        customerEntity.setStatus((String.valueOf(status.getStatusName())));
        customerEntity.setStatus((String.valueOf(status)));
        customerEntity.setIsActive(1);
        customerRepository.save(customerEntity);
        System.out.println("note : " + customerDTO.getDemand());
        if (customerDTO.getDemand() != null) {
            customerConverter.convertPostAndEditCustomer(customerDTO, customerEntity);
        }
    }


    @Override
    public void postAlls(CustomerDTO customerDTO , boolean check) throws MyException {
        CustomerEntity customerEntity = modelMapper.map(customerDTO, CustomerEntity.class);
        Status status = Status.CHUA_XU_LY;
//        customerEntity.setStatus((String.valueOf(status.getStatusName())));
        customerEntity.setStatus((String.valueOf(status)));
        customerEntity.setIsActive(1);
        customerRepository.save(customerEntity);
        System.out.println("note : " + customerDTO.getDemand());
        if (customerDTO.getDemand() != null) {
            customerConverter.convertPostAndEditCustomer(customerDTO, customerEntity);
        }
    }


    @Override
    public List<CustomerResponse> findAll(Map<String, Object> param, String statuses) {
        CustomerSearchBuilder customerSearchBuilder = customerSearchBuilderConveter.toCustomerSearchBuilder(param, statuses);
        System.out.println("id = " + customerSearchBuilder.getStaffId());
        List<CustomerEntity> customerEntities = customerRepository.findAll(customerSearchBuilder);
        List<CustomerResponse> customerResponses = new ArrayList<>();
        for (CustomerEntity customer : customerEntities) {
            String staff = customer.getBuildingEntities().stream()
                    .flatMap(building -> building.getUsers().stream())
                    .map(UserEntity::getFullName)
                    .distinct()
                    .collect(Collectors.joining(", "));

            // xu ly cac field con lai
            CustomerResponse customerResponse = CustomerResponse.builder()
                    .id(customer.getId())
                    .fullName(customer.getFullName())
                    .phone(customer.getPhone())
                    .email(customer.getEmail())
                    .staffId(staff)
                    .demand(customer.getDemand())
                    .status(Status.getStatusNames(customer.getStatus())) // Chuyển mã code sang tên
                    .createdBy(customer.getCreatedBy())
                    .createdDate(customer.getCreatedDate())
                    .is_Active(1)
                    .build();
            customerResponses.add(customerResponse);
        }
        return customerResponses;
    }

    @Override
    public List<TransactionResponse> findAllDDX(String code , Long id ) {
        List<TransactionEntity> transactionEntities = transactionRepository.findActiveCustomers(code,id);
        List<TransactionResponse> transactionResponses = new ArrayList<>();
        for (TransactionEntity transactionEntity : transactionEntities) {
            TransactionResponse transactionResponse = TransactionResponse.builder()
                    .note(transactionEntity.getNote())
                    .modifiedBy(transactionEntity.getModifiedBy())
                    .createdBy(transactionEntity.getCreatedBy())
                    .createdDate(transactionEntity.getCreatedDate())
//                    .code(Long.valueOf((TransactionType.valueOf(transactionEntity.getCode()).getType())))
                    .modifiedDate(transactionEntity.getModifiedDate())
//                    .staffId(transactionEntity.getStaffid())
                    .build();
            transactionResponses.add(transactionResponse);
        }
        return transactionResponses;
    }

    @Override
    public List<TransactionResponse> findAllCSKH(String code , Long id ) {
        List<TransactionEntity> transactionEntities = transactionRepository.findActiveCustomers(code,id);
        List<TransactionResponse> transactionResponses = new ArrayList<>();
        for (TransactionEntity transactionEntity : transactionEntities) {
            TransactionResponse transactionResponse = TransactionResponse.builder()
                    .note(transactionEntity.getNote())
                    .modifiedBy(transactionEntity.getModifiedBy())
                    .createdBy(transactionEntity.getCreatedBy())
                    .createdDate(transactionEntity.getCreatedDate())
//                    .code(Long.valueOf((TransactionType.valueOf(transactionEntity.getCode()).getType())))
                    .modifiedDate(transactionEntity.getModifiedDate())
//                    .staffId(transactionEntity.getStaffid())
                    .build();
            transactionResponses.add(transactionResponse);
        }
        return transactionResponses;
    }

    @Override
    public List<TransactionEntity> findTransactionsByCustomerId(Long customerId) {
        List<TransactionEntity> transactions = this.findTransactionsByCustomerId(customerId);
        System.out.println("Transactions size: " + transactions.size());
        transactions.forEach(t -> System.out.println("Transaction ID: " + t.getId() + ", Customer ID: " + t.getCustomer().getId()));

        return transactionRepository.findTransactionsByCustomerId(customerId);
    }




    @Override
    public List<CustomerDTO> getUser(String searchValue, Pageable pageable) {
        Page<CustomerEntity> customerEntities = null;
        if (StringUtils.isNotBlank(searchValue)) {
            customerEntities = customerRepository.findByFullNameContainingIgnoreCaseAndIsActiveNot(searchValue, 0, pageable);
        } else {
            customerEntities = customerRepository.findByIsActiveNot(0, pageable);
        }
        List<CustomerEntity> newsEntities = customerEntities.getContent();
        List<CustomerDTO> result = new ArrayList<>();
        for (CustomerEntity customerEntity : newsEntities) {
            CustomerDTO customerDTO = customerConverter.convertToDto(customerEntity);
            result.add(customerDTO);
        }
        System.out.println("Pageable: " + pageable);
        System.out.println("Data on this page: " + newsEntities);
        return result;
    }

    @Override
    public void deleteAll(Long[] ids) {
        try {
            List<CustomerEntity> customerEntities = customerRepository.findByIdIn(ids);
            if (transactionRepository != null) {
                transactionRepository.deleteAllByCustomerIn(customerEntities);
            }
            for(Long id : ids) {
                CustomerEntity customerEntity = customerRepository.findById(id).get();
                customerEntity.setIsActive(0);
                customerRepository.save(customerEntity);
            }

        } catch (Exception e) {
            throw new RuntimeException("Xoá Lỗi rồi", e);
        }
    }

    @Override
    public int countTotalItems() {
        return customerRepository.countTotalItem();
    }

    @Override
    public CustomerDTO getCustomerById(Long id) {
        CustomerEntity customerEntity = customerRepository.findById(id).orElse(null);
        return customerConverter.convertToDto(customerEntity);
    }


    @Override
    public void putAll(CustomerDTO customerDTO) {
        CustomerEntity customerEntity = customerRepository.findById(customerDTO.getId()).orElse(null);
        if (customerEntity != null) {
            List<TransactionEntity> transactionEntities = customerConverter.convertPostAndEditCustomer(customerDTO, customerEntity);
            customerEntity.getTransactions().addAll(transactionEntities);
            modelMapper.map(customerDTO, customerEntity);
            Status status = Status.valueOf(String.valueOf(customerDTO.getStatus()));
            System.out.println("status : " + status);
            customerEntity.setStatus(String.valueOf(status));

            customerRepository.save(customerEntity);
        } else {
            System.out.println("BuildingEntity không tồn tại với ID: " + customerDTO.getId());
        }
    }

    @Override
    public Object loadStaff(Long customerId) {
        List<UserEntity> userEntities = userRepository.findByStatusAndRoles_Code(1, "STAFF");
        Optional<CustomerEntity> customerEntity = customerRepository.findById(customerId);
        if (!customerEntity.isPresent()) {
            throw new RuntimeException("Toà nhà không tồn tại !!");
        }
        CustomerEntity customerEntity1 = customerEntity.get();
        List<StaffResponseDto> staffResponseDtos = new ArrayList<>();
        for (UserEntity userEntity : userEntities) {
            StaffResponseDto staffResponseDto = new StaffResponseDto();
            staffResponseDto.setStaffId(userEntity.getId());
            staffResponseDto.setUserName(userEntity.getUserName());
            boolean isAssigned = customerEntity1.getUsers().stream()
                    .anyMatch(assignedUser -> assignedUser.getId().equals(userEntity.getId()));
            staffResponseDto.setChecked(isAssigned ? "checked" : "unchecked");
            staffResponseDtos.add(staffResponseDto);
        }
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setData(staffResponseDtos);
        responseDTO.setMessage("OKEEE");

        return responseDTO;
    }

    @Override
    public Object saveStaff(AssigmentCustomerDTO assigmentCustomerDTO) {
        CustomerEntity customerEntity = customerRepository.findById(assigmentCustomerDTO.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Khách Hàng không tồn tại !!"));
        customerEntity.getUsers().clear();
        customerRepository.save(customerEntity);
        for (Long staffId : assigmentCustomerDTO.getStaffs()) {
            UserEntity userEntity = userRepository.findById(staffId)
                    .orElseThrow(() -> new RuntimeException("Nhân viên không tồn tại"));
            customerEntity.getUsers().add(userEntity);
        }
        customerRepository.save(customerEntity);
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setMessage("Lưu nhân viên thành công");
        return responseDTO;
    }

}
