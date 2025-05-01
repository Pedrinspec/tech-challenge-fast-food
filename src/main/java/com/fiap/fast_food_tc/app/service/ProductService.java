package com.fiap.fast_food_tc.app.service;

import com.fiap.fast_food_tc.adapter.dto.ProductRequestDto;
import com.fiap.fast_food_tc.adapter.dto.ProductResponseDto;
import com.fiap.fast_food_tc.cross.ProductMapper;
import com.fiap.fast_food_tc.domain.entity.EProduct;
import com.fiap.fast_food_tc.domain.usecase.ProductUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductUseCase productUseCase;

    private final ProductMapper productMapper;

    @Autowired
    public ProductService(ProductUseCase productUseCase, ProductMapper productMapper) {
        this.productUseCase = productUseCase;
        this.productMapper = productMapper;
    }

    public ProductResponseDto create(ProductRequestDto product) {
        EProduct product1 = productUseCase.create(productMapper.messageToEntity(product));
        return productMapper.entityToMessage(product1);
    }


    public ProductResponseDto update(long id, ProductRequestDto product) {
        EProduct product1 = productMapper.messageToEntity(product);
        product1.setProductId(id);
        return productMapper.entityToMessage(productUseCase.update(product1));
    }

    public void delete(long id) {
        productUseCase.delete(id);
    }

    public List<ProductResponseDto> getByCategoryId(long categoryId) {

        List<ProductResponseDto> productList = productUseCase.getByCategoryId(categoryId)
                .stream()
                .map(user -> productMapper.entityToMessage(user))
                .collect(Collectors.toList());


        return productList;
    }
}
