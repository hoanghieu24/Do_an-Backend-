package com.javaweb.service.impl;

import com.javaweb.model.dto.ProductDto;
import com.javaweb.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
public class ProductServiceimpl implements ProductService {
    @Override
    public List<ProductDto> findAll(Map<String, Object> param, String image) {
        return Collections.emptyList();
    }
}
