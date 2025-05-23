package com.fiap.fast_food_tc.unit.app.service;

import com.fiap.fast_food_tc.adapter.provider.ProductDataProvider;
import fixture.ProductFixture;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


class ProductServiceTest {

    private ProductDataProvider productService;

    @Test
    void findAllReturnMappedProductResponseList(){
        // Arrange
        var product = ProductFixture.createProduct();
        var productResponse = ProductFixture.createProduct();
       // when(productUseCase.findAll()).thenReturn(List.of(product));
      //  when(productMapper.toResponseList(List.of(product))).thenReturn(List.of(productResponse));

        // Act
        var result = productService.findAll();

        // Assert
        assertEquals(1, result.size());
        assertEquals(productResponse, result.get(0));
    }

}