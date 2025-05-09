package com.fiap.fast_food_tc.app.service;

import com.fiap.fast_food_tc.adapter.dto.product.ProductRequest;
import com.fiap.fast_food_tc.adapter.dto.product.ProductResponse;
import com.fiap.fast_food_tc.cross.ProductMapper;
import com.fiap.fast_food_tc.domain.usecase.ProductUseCase;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductMapper productMapper;

    private final ProductUseCase productUseCase;

    @Autowired
    public ProductService(ProductMapper productMapper, ProductUseCase productUseCase) {
        this.productMapper = productMapper;
        this.productUseCase = productUseCase;
    }

    public ProductResponse findById(Integer id) {
        return productMapper.toResponse(productUseCase.findById(id));
    }

    public List<ProductResponse> findAll() {
        return productMapper.toResponseList(productUseCase.findAll());
    }

    public ProductResponse create(@Valid ProductRequest productRequest) {
        var product = productMapper.toEntityCreate(productRequest);
        return productMapper.toResponse(productUseCase.create(product));
    }

    public ProductResponse update(Integer id, ProductRequest dto) {
        var product = productMapper.toEntityCreate(dto);
        return productMapper.toResponse(productUseCase.updateCustomer(id, product));
    }

    public void delete(Integer id) {
        productUseCase.deleteProduct(id);
    }

}
