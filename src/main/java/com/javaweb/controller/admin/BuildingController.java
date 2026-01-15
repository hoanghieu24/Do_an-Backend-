package com.javaweb.controller.admin;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.constant.SystemConstant;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.RentareaEntity;
import com.javaweb.enums.buildingType;
import com.javaweb.enums.districtCode;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.dto.UserDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.response.BuildingResponse;
import com.javaweb.repository.RentareaRepository;
import com.javaweb.security.utils.SecurityUtils;
import com.javaweb.service.BuildingService;
import com.javaweb.service.IUserService;
import com.javaweb.service.impl.BuildingServiceimpl;
import com.javaweb.utils.DisplayTagUtils;
import com.javaweb.utils.MessageUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.nio.file.AccessDeniedException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@CrossOrigin(origins = "http://localhost:8080")  // Giúp Vue.js có thể gọi API từ localhost
@RestController  // Sử dụng @RestController để trả về JSON
@RequestMapping("/api/buildings")  // Endpoint này sẽ trả về các tòa nhà
public class BuildingController {

    @Autowired
    private IUserService iUserService;

    @Autowired
    private BuildingService buildingService;


    private BuildingEntity buildingEntity;
    @Autowired
    private RentareaRepository rentareaRepository;
    @Autowired
    private MessageUtils messageUtils;

//    @GetMapping
//    public List<BuildingDTO> getBuildings() {
//        return buildingService.getAllBuildings();
//    }
    @GetMapping
    public List<BuildingResponse> getAllBuildings(@ModelAttribute(name = "ModelSearch") BuildingSearchRequest params, HttpServletRequest request,
                                                  @RequestParam Map<String, Object> paramMap ) {
    // Giả sử bạn có phương thức này trong service để lấy danh sách các tòa nhà
    return buildingService.findAll(paramMap,params.getTypeCode());
    }
    @GetMapping("/admin/building-list")
    public ModelAndView buildingList(@ModelAttribute(name = "ModelSearch") BuildingSearchRequest params, HttpServletRequest request,
                                     @RequestParam Map<String, Object> paramMap ) {
        ModelAndView view = new ModelAndView("admin/building/list");


        view.addObject("district", districtCode.type());
        view.addObject("rentype", buildingType.type());
        view.addObject("staffs", iUserService.liststaff());
        DisplayTagUtils.of(request, params);
        // Gọi phương thức tìm kiếm với params và điều kiện từ paramMap
        if(SecurityUtils.getAuthorities().contains("ROLE_STAFF")){
            paramMap.put("staffId", SecurityUtils.getPrincipal().getId());
        }
        List<BuildingResponse> responses = buildingService.findAll(paramMap,params.getTypeCode());
        List<BuildingDTO> news = buildingService.getBuilding(params.getSearchValue(), PageRequest.of(params.getPage() - 1, params.getMaxPageItems()));
        BuildingResponse buildingResponse = new BuildingResponse();
        params.setListResult(news);
        params.setTotalItems(buildingService.countTotalItems());
        view.addObject(SystemConstant.MODEL, params);
        initMessageResponse(view, request);

        view.addObject("buildings", responses);  // Thêm dữ liệu tòa nhà vào Model
        view.addObject("ModelSearch", params);
        return view;
    }


//    @GetMapping("/admin/building-list")
//    public ModelAndView searchBuilding(@RequestParam Map<String, Object> params) {
//        ModelAndView mav = new ModelAndView("admin/building/list"); // Tên view của bạn
//        List<BuildingResponse> buildings = buildingService.findAll(params, null); // `params` chứa các tiêu chí tìm kiếm
//        mav.addObject("buildings", buildings); // Thêm danh sách tòa nhà vào Model
//        return mav;
//    }

    @GetMapping("/admin/building-edit")
    public ModelAndView buildingEdit(@ModelAttribute(name = "buildingEdit" ) BuildingDTO buildingDto) {
        ModelAndView view = new ModelAndView("admin/building/edit");
        view.addObject("district", districtCode.type());
        view.addObject("rentype", buildingType.type());

        return view;
    }

    @GetMapping("/admin/building-edit-{id}")
    public ModelAndView buildingEdit(@PathVariable Long id) throws AccessDeniedException {
        BuildingDTO buildingDTO = buildingService.getBuildingById(id);
        ModelAndView view = new ModelAndView("admin/building/editlist");
        view.addObject("district", districtCode.type());
        System.out.println("district : " + districtCode.type());
        view.addObject("rentype", buildingType.type());
        view.addObject("buildingEdit", buildingDTO);
        return view;
    }

    @GetMapping("/admin/building-list-{id}")
    public ModelAndView buildingDelete(@PathVariable Long id) throws AccessDeniedException {
        BuildingDTO buildingDTO = buildingService.getBuildingById(id); // Lấy thông tin tòa nhà từ service
        ModelAndView view = new ModelAndView("admin/building/list");
        view.addObject("district", districtCode.type());
        view.addObject("rentype", buildingType.type());
        view.addObject("staffs", iUserService.liststaff());
        view.addObject("ModelSearch", buildingDTO); // Gửi BuildingDTO đã cập nhật vào view
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

    @GetMapping("/{id}")
    public ResponseEntity<BuildingDTO> getBuildingById(@PathVariable Long id) throws AccessDeniedException {
        BuildingDTO building = buildingService.getBuildingById(id);
        if (building != null) {
            return ResponseEntity.ok(building);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}/favourite")
    public ResponseEntity<BuildingDTO> getBuildingFavouriteById(@PathVariable Long id) throws AccessDeniedException {
        BuildingDTO building = buildingService.getBuildingFavouriteById(id);
        if (building != null) {
            return ResponseEntity.ok(building);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/districts")
    public ResponseEntity<Map<String, String>> getDistricts() {
        return ResponseEntity.ok(districtCode.type());  // Trả về Map<String, String>
    }

    @GetMapping("/rent-types")
    public ResponseEntity<Map<String, String>> getRentTypes() {
        return ResponseEntity.ok(buildingType.type());
    }

//    @GetMapping("/get-staffs")  // 👈 Đặt tên không trùng với {id}
//    public ResponseEntity<List<UserDTO>> getStaffs() {
//        return ResponseEntity.ok(iUserService.liststaff());
//    }
    @GetMapping("/staffs") // đường dẫn rõ ràng, không bị trùng với {id}
    public ResponseEntity<?> getAllStaffs() {
        return ResponseEntity.ok(iUserService.liststaff()); // hoặc dữ liệu nào bạn cần
    }

    @Controller
    public class FrontendController {

        @RequestMapping(value = { "/", "/{path:[^\\.]*}" })
        public String forward() {
            return "forward:/index.html";
        }
    }



//    @GetMapping("/staffs")
//    public ResponseEntity<List<UserDTO>> getStaffs() {
//        return ResponseEntity.ok((List<UserDTO>) iUserService.liststaff());
//    }



}

