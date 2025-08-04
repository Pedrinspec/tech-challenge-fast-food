package com.fiap.fast_food_tc.application.usecase.impl;

import com.fiap.fast_food_tc.domain.entity.Product;
import com.fiap.fast_food_tc.infrastructure.web.mapper.ProductMapper;
import com.fiap.fast_food_tc.application.gateway.ProductGateway;
import com.fiap.fast_food_tc.application.usecase.ProductUseCase;
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
    public Product create(Product product) {
        return productMapper.toEntity(productGateway.create(productMapper.toModel(product)));
    }

    @Override
    public List<Product> findByCategoryId(Integer categoryId) {
        return productMapper.toEntityList(productGateway.findByCategoryId(categoryId));
    }

    @Override
    public Product findById(Integer id) {
        return productMapper.toEntity(productGateway.findById(id));
    }

    @Override
    public List<Product> findAll() {
        return productMapper.toEntityList(productGateway.findAll());
    }

    @Override
    public Product updateCustomer(Integer id, Product dto) {
        dto.setProductId(id);
        return productMapper.toEntity(productGateway.update(productMapper.toModel(dto)));
    }

    @Override
    public void deleteProduct(Integer id) {
        productGateway.delete(id);
    }

    @Override
    public void subtractQuantity(Integer id, Integer quantityToRemove) {
        Product product = productMapper.toEntity(productGateway.findById(id));
        Integer newQuantity = product.getQuantity()-quantityToRemove;
        productGateway.substractQuantity(id, newQuantity);
    }
}
