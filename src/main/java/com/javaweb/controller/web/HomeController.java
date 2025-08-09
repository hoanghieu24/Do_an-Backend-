package com.javaweb.controller.web;

import com.javaweb.constant.SystemConstant;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.enums.buildingType;
import com.javaweb.enums.districtCode;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.response.BuildingResponse;
import com.javaweb.repository.RentareaRepository;
import com.javaweb.security.utils.SecurityUtils;
import com.javaweb.service.BuildingService;
import com.javaweb.service.IUserService;
import com.javaweb.service.ProductService;
import com.javaweb.utils.DisplayTagUtils;
import com.javaweb.utils.DistrictCode;
import com.javaweb.utils.MessageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

@Controller(value = "homeControllerOfWeb")
//@Controller
//@RequestMapping("/api/product")
public class HomeController {

    @Autowired
    private ProductService productService;

    @Autowired
    private IUserService iUserService;

    @Autowired
    private BuildingService buildingService;


    private BuildingEntity buildingEntity;
    @Autowired
    private RentareaRepository rentareaRepository;
    @Autowired
    private MessageUtils messageUtils;

	@RequestMapping(value = "/trang-chu", method = RequestMethod.GET)
	public ModelAndView homePage( HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("web/home");
        mav.addObject("modelSearch");
        mav.addObject("districts", DistrictCode.type());
		return mav;
	}

    @GetMapping("/{ids}")
    private Object loadProduct(@PathVariable Long ids){
        return buildingService.loadProduct(ids);
    }

    @GetMapping(value="/gioi-thieu")
    public ModelAndView introducceBuiding(){
        ModelAndView mav = new ModelAndView("web/introduce");
        return mav;
    }

    @GetMapping(value = "/san-pham")
    public ModelAndView showProducts(
            @ModelAttribute(name = "ModelSearch") BuildingSearchRequest params,
            HttpServletRequest request,
            @RequestParam Map<String, Object> paramMap) {
        ModelAndView view = new ModelAndView("web/list");

        // Thêm dữ liệu vào view
        view.addObject("district", districtCode.type());  // Quận
        view.addObject("rentype", buildingType.type());   // Loại tòa nhà
        view.addObject("staffs", iUserService.liststaff());  // Danh sách nhân viên

        // Xử lý tham số tìm kiếm
        DisplayTagUtils.of(request, params);

        // Thêm filter staffId nếu có quyền 'ROLE_STAFF'
        if (SecurityUtils.getAuthorities().contains("ROLE_STAFF")) {
            paramMap.put("staffId", SecurityUtils.getPrincipal().getId());
        }

        // Lấy danh sách tòa nhà
        List<BuildingResponse> responses = buildingService.findAll(paramMap, params.getTypeCode());

        // Lấy danh sách sản phẩm với phân trang
        List<BuildingDTO> news = buildingService.getBuilding(
                params.getSearchValue(),
                PageRequest.of(params.getPage() - 1, params.getMaxPageItems())
        );

        // Cập nhật số sản phẩm và số trang
        params.setListResult(news);
        int totalItems = buildingService.countTotalItems();
        params.setTotalItems(totalItems);
        int totalPages = (int) Math.ceil((double) totalItems / params.getMaxPageItems());
        params.setTotalPage(totalPages);

        // Định dạng giá thuê
        DecimalFormat decimalFormat = new DecimalFormat("#,###.###");
        for (BuildingResponse response : responses) {
            if (response.getRentPrice() != null) {
                response.setFormattedRentPrice(decimalFormat.format(response.getRentPrice()));
            }
        }

        // Thêm dữ liệu vào model
        view.addObject(SystemConstant.MODEL, params);
        view.addObject("buildings", responses);
        view.addObject("ModelSearch", params);

        return view;
    }




    @GetMapping(value="/tin-tuc")
    public ModelAndView news(){
        ModelAndView mav = new ModelAndView("/web/news");
        return mav;
    }

    @GetMapping(value="/lien-he")
    public ModelAndView contact(){
        ModelAndView mav = new ModelAndView("/web/contact");
        return mav;
    }

    @GetMapping(value="/bot-chat")
    public ModelAndView HDO_AI(){
        ModelAndView mav = new ModelAndView("/web/chatBot");
        return mav;
    }

    @RequestMapping(value = "/errors", method = RequestMethod.GET)
    public ModelAndView erroe(){
        ModelAndView mav = new ModelAndView("redirect:/web/Error");
        return mav;
    }

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login() {
		ModelAndView mav = new ModelAndView("login");
		return mav;
	}

	@RequestMapping(value = "/access-denied", method = RequestMethod.GET)
	public ModelAndView accessDenied() {
		return new ModelAndView("redirect:/login?accessDenied");
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return new ModelAndView("redirect:/trang-chu");
	}
}
