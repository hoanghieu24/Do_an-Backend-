package com.javaweb.controller.admin;

import com.javaweb.constant.SystemConstant;
import com.javaweb.exception.MyException;
import com.javaweb.model.dto.UserDTO;
import com.javaweb.security.utils.SecurityUtils;
import com.javaweb.service.IUserService;
import com.javaweb.service.impl.RoleService;
import com.javaweb.utils.DisplayTagUtils;
import com.javaweb.utils.MessageUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import com.javaweb.model.dto.MyUserDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.http.HttpStatus;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
@RestController // THÊM controller REST tách biệt với các trang HTML
@RequestMapping("/api")
@Controller(value = "usersControllerOfAdmin")
public class UserController {

	@Autowired
	private IUserService userService;

	@Autowired
	private RoleService roleService;

	@Autowired
	private MessageUtils messageUtil;

	@RequestMapping(value = "/admin/user-list", method = RequestMethod.GET)
	public ModelAndView getNews(@ModelAttribute(SystemConstant.MODEL) UserDTO model, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("admin/user/list");
		DisplayTagUtils.of(request, model);
		List<UserDTO> news = userService.getUsers(model.getSearchValue(), PageRequest.of(model.getPage() - 1, model.getMaxPageItems()));
		model.setListResult(news);
		model.setTotalItems(userService.countTotalItems());
		mav.addObject(SystemConstant.MODEL, model);
		initMessageResponse(mav, request);
		return mav;
	}

	@RequestMapping(value = "signup", method = RequestMethod.GET)
	public ModelAndView addUser(@ModelAttribute(SystemConstant.MODEL) UserDTO model, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("signup");
		model.setRoleDTOs(roleService.getRoles());
		initMessageResponse(mav, request);
		mav.addObject("rolecode", roleService.getRoles());
		mav.addObject(SystemConstant.MODEL, model);
		return mav;
	}


	@RequestMapping(value = "/admin/profile-{username}", method = RequestMethod.GET)
	public ModelAndView updateProfile(@PathVariable("username") String username, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("admin/user/profile");
		UserDTO model = userService.findOneByUserName(username);
		initMessageResponse(mav, request);
		model.setRoleDTOs(roleService.getRoles());
		mav.addObject(SystemConstant.MODEL, model);
		return mav;
	}

	@RequestMapping(value = "/admin/user-edit-{id}", method = RequestMethod.GET)
	public ModelAndView updateUser(@PathVariable("id") Long id, HttpServletRequest request) throws MyException {
		ModelAndView mav = new ModelAndView("admin/user/edit");
		UserDTO model = userService.findUserById(id);
		if (model == null) {
			throw new MyException("User not found");
		}
		mav.addObject(SystemConstant.MODEL, model);
		model.setRoleDTOs(roleService.getRoless());
		initMessageResponse(mav, request);


		mav.addObject("rolecode", roleService.getRoless());

		return mav;
	}


//	@RequestMapping(value = "/admin/user-edit-{id}", method = RequestMethod.GET)
//	public ModelAndView updateUser(@PathVariable("id") Long id, HttpServletRequest request,UserDTO model) throws MyException {
//		ModelAndView mav = new ModelAndView("admin/user/edit");
//		model.setRoleDTOs(roleService.getRoless());
//		initMessageResponse(mav, request);
//		mav.addObject("rolecode", roleService.getRoless());
//		mav.addObject(SystemConstant.MODEL, model);
//		return mav;
//	}


	@RequestMapping(value = "/admin/profile-password", method = RequestMethod.GET)
	public ModelAndView updatePassword(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("admin/user/password");
		UserDTO model = userService.findOneByUserName(SecurityUtils.getPrincipal().getUsername());
		initMessageResponse(mav, request);
		mav.addObject(SystemConstant.MODEL, model);
		return mav;
	}

	private void initMessageResponse(ModelAndView mav, HttpServletRequest request) {
		String message = request.getParameter("message");
		if (message != null && StringUtils.isNotEmpty(message)) {
			Map<String, String> messageMap = messageUtil.getMessage(message);
			mav.addObject(SystemConstant.ALERT, messageMap.get(SystemConstant.ALERT));
			mav.addObject(SystemConstant.MESSAGE_RESPONSE, messageMap.get(SystemConstant.MESSAGE_RESPONSE));
		}
	}
	@GetMapping("/current-user")
	public ResponseEntity<?> getCurrentUserInfo() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (authentication != null && authentication.getPrincipal() instanceof MyUserDetail) {
			MyUserDetail user = (MyUserDetail) authentication.getPrincipal();

			Map<String, Object> response = new HashMap<>();
			response.put("id", user.getId());  // chính là customerId
			response.put("username", user.getUsername());
//			response.put("fullName", user.getFullName());


			return ResponseEntity.ok(response);
		}

		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Chưa đăng nhập");
	}
}
