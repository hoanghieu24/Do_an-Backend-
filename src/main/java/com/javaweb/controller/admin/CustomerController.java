package com.javaweb.controller.admin;

import com.javaweb.constant.SystemConstant;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.CustomerEntity;
import com.javaweb.entity.UserEntity;
import com.javaweb.enums.Status;
import com.javaweb.model.dto.CustomerDTO;
import com.javaweb.model.dto.TransactionDTO;
import com.javaweb.model.request.CustomerSearchRequest;
import com.javaweb.model.response.CustomerResponse;
import com.javaweb.model.response.TransactionResponse;
import com.javaweb.repository.CustomerRepository;
import com.javaweb.repository.RentareaRepository;
import com.javaweb.repository.TransactionRepository;
import com.javaweb.repository.UserRepository;
import com.javaweb.security.utils.SecurityUtils;
import com.javaweb.service.*;
import com.javaweb.utils.DisplayTagUtils;
import com.javaweb.utils.MessageUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
public class CustomerController {

    @Autowired
    private IUserService iUserService;

    @Autowired
    private BuildingService buildingService;
    private BuildingEntity buildingEntity;
    @Autowired
    private RentareaRepository rentareaRepository;
    @Autowired
    private MessageUtils messageUtils;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TransactionService transactionService;
//    @Autowired
//    private SignTheContractService signTheContractService;

    @GetMapping("/admin/staff-list")
    public ModelAndView CustomerList(@ModelAttribute(name = "ModelSearchs") CustomerSearchRequest params, CustomerDTO model,
                                     HttpServletRequest request, @RequestParam Map<String, Object> paramMap) {
        ModelAndView view = new ModelAndView("admin/staff/list");
        DisplayTagUtils.of(request, model);
        if(SecurityUtils.getAuthorities().contains("ROLE_STAFF")){
            paramMap.put("staffId", SecurityUtils.getPrincipal().getId());
            System.out.println(params.getStaffId());

        }

        List<CustomerDTO> news = customerService.getUser(model.getSearchValue(), PageRequest.of(model.getPage() - 1, model.getMaxPageItems()));
        model.setListResult(news);
        model.setTotalItems(customerService.countTotalItems());
        view.addObject(SystemConstant.MODEL, model);
        view.addObject("staffs", iUserService.liststaff());


        List<CustomerResponse> responses = customerService.findAll(paramMap, (params.getStatus()));
//        view.addObject(SystemConstant.MODEL, model);
        view.addObject("status", Status.type());
        view.addObject("customs", responses);
        view.addObject("ModelSearchs", params);
        initMessageResponse(view, request);
        return view;
    }



    @GetMapping("/admin/staff-edit")
    public ModelAndView createCustomer(@ModelAttribute(name = "CustomerEdit") CustomerDTO customerDTO) {
        ModelAndView view = new ModelAndView("admin/staff/edit");
        try {
            if (customerDTO.getPhone() != null) {
                Optional<CustomerEntity> existingCustomer = customerRepository.findByPhone(customerDTO.getPhone());
                if (existingCustomer.isPresent()) {
                    throw new IllegalArgumentException("Số điện thoại đã tồn tại trong hệ thống.");
                }
            }
        } catch (IllegalArgumentException e) {
            view.addObject("errorMessage", e.getMessage());
            view.addObject("showErrorModal", true);
        }
        view.addObject("status", Status.type());
        return view;
    }

    @GetMapping("/web/contact")
    public ModelAndView CreateCustomerNoLogin(@ModelAttribute(name = "CustomerEdit" ) TransactionDTO transactionDTO) {
        ModelAndView view = new ModelAndView("web/contact");
        view.addObject("CustomerEdit", transactionDTO);
        view.addObject("status", Status.CHUA_XU_LY);
        System.out.println("tinh trang : " + Status.type());
        return view;
    }

    @GetMapping("/admin/staff-edit-{id}")
    public ModelAndView CustomerEdit(@PathVariable Long id, CustomerDTO model , TransactionDTO params
                                    ,  @RequestParam Map<String, Object> paramMap ){
        CustomerDTO customerDTO = customerService.getCustomerById(id);
        TransactionDTO transactionDTO = transactionService.getTranById(id);
        ModelAndView view = new ModelAndView("admin/staff/editlist");
        CustomerEntity customerEntity = customerRepository.findByIdAndIsActive(id, 1);

        if(SecurityUtils.getAuthorities().contains("ROLE_STAFF")){
            UserEntity userEntity = userRepository.findById(SecurityUtils.getPrincipal().getId()).get();
            if(!customerEntity.getUsers().contains(userEntity)){
                return new ModelAndView("error/Error");
            }
        }

        view.addObject(SystemConstant.MODEL, model);
        view.addObject("status", Status.type());
        view.addObject("CustomerEdit", customerDTO);
        view.addObject("TransactionEdit", transactionDTO);
        List<TransactionResponse> CSKH = transactionService.findAllCSKH(paramMap, params.getCode(),id);
        List<TransactionResponse> DDX = transactionService.findAllDDX(paramMap, params.getCode(),id);
//        List<SignTheContractReponse> KHD = signTheContractService.findAllKHD(paramMap ,id );
        List<CustomerDTO> news = customerService.getUser(model.getSearchValue(), PageRequest.of(model.getPage() - 1, model.getMaxPageItems()));
        model.setListResult(news);
//        view.addObject("KHD",KHD);
        view.addObject("CSKH", CSKH);
        view.addObject("DDX", DDX);

        return view;
    }


    private void initMessageResponse(ModelAndView mav, HttpServletRequest request) {
        String message = request.getParameter("message");
        if (message != null && StringUtils.isNotEmpty(message)) {
            Map<String, String> messageMap = messageUtils.getMessage(message);
            mav.addObject(SystemConstant.ALERT, messageMap.get(SystemConstant.ALERT));
            mav.addObject(SystemConstant.MESSAGE_RESPONSE, messageMap.get(SystemConstant.MESSAGE_RESPONSE));
        }
    }
}