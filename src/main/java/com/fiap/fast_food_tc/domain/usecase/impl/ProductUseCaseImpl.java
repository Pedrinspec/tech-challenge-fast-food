package com.fiap.fast_food_tc.domain.usecase.impl;

import com.fiap.fast_food_tc.cross.ProductMapper;
import com.fiap.fast_food_tc.domain.entity.EProduct;
import com.fiap.fast_food_tc.domain.gateway.ProductGateway;
import com.fiap.fast_food_tc.domain.usecase.ProductUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductUseCaseImpl implements ProductUseCase {

    private final ProductGateway productGateway;

    private final ProductMapper productMapper;

    @Autowired
    public ProductUseCaseImpl(ProductGateway productGateway, ProductMapper productMapper) {
        this.productGateway = productGateway;
        this.productMapper = productMapper;
    }

    @Override
    public EProduct create(EProduct eProduct) {
        return productMapper.toEntity(productGateway.create(productMapper.toModel(eProduct)));
    }

    @Override
    public EProduct findById(Integer id) {
        return productMapper.toEntity(productGateway.findById(id));
    }

    @Override
    public List<EProduct> findAll() {
        return productMapper.toEntityList(productGateway.findAll());
    }

    @Override
    public EProduct updateCustomer(Integer id, EProduct dto) {
        return productMapper.toEntity(productGateway.update(productMapper.toModel(dto)));
    }

    @Override
    public void deleteProduct(Integer id) {
        productGateway.delete(id);
    }

}
