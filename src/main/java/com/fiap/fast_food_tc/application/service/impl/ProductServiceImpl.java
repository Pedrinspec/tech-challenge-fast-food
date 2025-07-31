package com.fiap.fast_food_tc.application.service.impl;

import com.fiap.fast_food_tc.application.dto.product.in.ProductRequest;
import com.fiap.fast_food_tc.application.dto.product.out.ProductResponse;
import com.fiap.fast_food_tc.application.service.ProductService;
import com.fiap.fast_food_tc.infrastructure.web.mapper.ProductMapper;
import com.fiap.fast_food_tc.application.usecase.ProductUseCase;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductMapper productMapper;

    private final ProductUseCase productUseCase;

    @Autowired
    public ProductServiceImpl(ProductMapper productMapper, ProductUseCase productUseCase) {
        this.productMapper = productMapper;
        this.productUseCase = productUseCase;
    }

    @Override
    public ProductResponse findById(Integer id) {
        return productMapper.toResponse(productUseCase.findById(id));
    }

    @Override
    public List<ProductResponse> findByCategoryId(Integer categoryId) {
        return productMapper.toResponseList(productUseCase.findByCategoryId(categoryId));
    }

    @Override
    public List<ProductResponse> findAll() {
        return productMapper.toResponseList(productUseCase.findAll());
    }

    @Override
    public ProductResponse create(@Valid ProductRequest productRequest) {
        var product = productMapper.toEntityCreate(productRequest);
        return productMapper.toResponse(productUseCase.create(product));
    }

    @Override
    public ProductResponse update(Integer id, ProductRequest dto) {
        var product = productMapper.toEntityCreate(dto);
        return productMapper.toResponse(productUseCase.updateCustomer(id, product));
    }

    @Override
    public void delete(Integer id) {
        productUseCase.deleteProduct(id);
    }

}
