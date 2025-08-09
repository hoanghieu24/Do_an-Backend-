package com.javaweb.converter;

import com.javaweb.model.dto.UserDTO;
import com.javaweb.entity.UserEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {

    @Autowired
    private ModelMapper modelMapper;

    public UserDTO convertToDto(UserEntity entity) {
        if (entity == null) {
            return null;
        }
        try {
            UserDTO result = modelMapper.map(entity, UserDTO.class);
            // Xử lý mapping roles nếu cần
            if (entity.getRoles() != null && !entity.getRoles().isEmpty()) {
                result.setRoleCode(entity.getRoles().get(0).getCode());
            }
            return result;
        } catch (Exception e) {
            throw new RuntimeException("Convert user entity to DTO failed", e);
        }
    }

    public UserEntity convertToEntity(UserDTO dto) {
        if (dto == null) {
            return null;
        }
        try {
            return modelMapper.map(dto, UserEntity.class);
        } catch (Exception e) {
            throw new RuntimeException("Convert user DTO to entity failed", e);
        }
    }
}
