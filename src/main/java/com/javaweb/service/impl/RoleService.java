package com.javaweb.service.impl;

import com.javaweb.converter.RoleConverter;
import com.javaweb.converter.UserConverter;
import com.javaweb.entity.UserEntity;
import com.javaweb.model.dto.RoleDTO;
import com.javaweb.entity.RoleEntity;
import com.javaweb.model.dto.UserDTO;
import com.javaweb.repository.RoleRepository;
import com.javaweb.repository.UserRepository;
import com.javaweb.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RoleService implements IRoleService {

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private RoleConverter roleConverter;
	@Autowired
	private UserConverter userConverter;
	@Autowired
	private UserRepository userRepository;

	public List<RoleDTO> findAll() {
		List<RoleEntity> roleEntities = roleRepository.findAll();
		List<RoleDTO> list = new ArrayList<>();
		roleEntities.forEach(item -> {
			RoleDTO roleDTO = roleConverter.convertToDto(item);
			list.add(roleDTO);
		});
		return list;
	}

	@Override
	public Map<String, String> getRoles() {

		Map<String, String> roleTerm = new HashMap<>();
		UserEntity userEntity = new UserEntity();
		List<RoleEntity> roleEntity = roleRepository.findAll();
		for (RoleEntity item : roleEntity) {
			RoleDTO roleDTO = roleConverter.convertToDto(item);
			UserDTO userDTO = userConverter.convertToDto(userEntity);
				roleTerm.put("USER", "Người Dùng ");
		}
		return roleTerm;
	}
		@Override
		public Map<String, String> getRoless () {
			Map<String, String> roleTerm = new HashMap<>();
			List<RoleEntity> roleEntity = roleRepository.findAll();
			for (RoleEntity item : roleEntity) {
				RoleDTO roleDTO = roleConverter.convertToDto(item);
				roleTerm.put(roleDTO.getCode(), roleDTO.getName());
				System.out.println(roleTerm);
			}
			return roleTerm;
		}

}
