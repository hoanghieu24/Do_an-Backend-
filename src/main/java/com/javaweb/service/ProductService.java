package com.javaweb.service;

import com.javaweb.model.dto.ProductDto;
import com.javaweb.model.response.BuildingResponse;

import java.util.List;
import java.util.Map;

public interface ProductService {
    List<ProductDto> findAll(Map<String, Object> param, String image);
}
