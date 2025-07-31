package com.fiap.fast_food_tc.application.service;

import com.fiap.fast_food_tc.application.dto.product.in.ProductRequest;
import com.fiap.fast_food_tc.application.dto.product.out.ProductResponse;
import jakarta.validation.Valid;

import java.util.List;

public interface ProductService {

    ProductResponse findById(Integer id);

    List<ProductResponse> findByCategoryId(Integer categoryId);

    List<ProductResponse> findAll();

    ProductResponse create(@Valid ProductRequest productRequest);

    ProductResponse update(Integer id, ProductRequest dto);

    void delete(Integer id);
}
