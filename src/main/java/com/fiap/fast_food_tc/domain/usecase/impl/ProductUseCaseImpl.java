package com.fiap.fast_food_tc.domain.usecase.impl;

import com.fiap.fast_food_tc.adapter.db.model.Product;
import com.fiap.fast_food_tc.adapter.dto.ProductResponseDto;
import com.fiap.fast_food_tc.cross.ProductMapper;
import com.fiap.fast_food_tc.domain.entity.EProduct;
import com.fiap.fast_food_tc.domain.gateway.ProductGateway;
import com.fiap.fast_food_tc.domain.usecase.ProductUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        return  productMapper.toEntity(productGateway.create(productMapper.toDomain(eProduct)));

    }



    @Override
    public EProduct update(EProduct eProduct) {
        Optional<Product> existingProduct = productGateway.findById(eProduct.getProductId());
        if(existingProduct.isPresent()){
            return productMapper.toEntity(productGateway.update(productMapper.toDomain(eProduct)));

        } else {
            throw new RuntimeException("Product not found");
        }

    }

    @Override
    public void delete(Long id) {
        Optional<Product> existingProduct = productGateway.findById(id);
        if(existingProduct.isPresent()){
            productGateway.delete(existingProduct.get());

        } else {
            throw new RuntimeException("Product not found");
        }
    }

    @Override
    public List<EProduct> getByCategoryId(long categoryId) {
        List<EProduct> productList = productGateway.getByCategoryId(categoryId)
                .stream()
                .map(user -> productMapper.toEntity(user))
                .collect(Collectors.toList());


        return  productList ;
    }

}
