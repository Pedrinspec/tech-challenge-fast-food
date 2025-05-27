package com.fiap.fast_food_tc.unit.app.service;

import com.fiap.fast_food_tc.app.service.impl.ProductServiceImpl;
import com.fiap.fast_food_tc.cross.mapper.ProductMapper;
import com.fiap.fast_food_tc.domain.usecase.ProductUseCase;
import fixture.ProductFixture;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static fixture.ProductFixture.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductMapper productMapper;

    @Mock
    private ProductUseCase productUseCase;

    @InjectMocks
    private ProductServiceImpl productServiceImpl;


    @Test
    void findByIdSuccessTest() {
        Mockito.when(productMapper.toResponse(any())).thenReturn(createProductResponse());
        Mockito.when(productUseCase.findById(createEProduct().getProductId())).thenReturn(createEProduct());

        var response = productServiceImpl.findById(createEProduct().getProductId());

        assertNotNull(response);
        assertEquals(createProductResponse().getProductId(), response.getProductId());
    }




    @Test
    void findAllSuccessTest() {
        Mockito.when(productMapper.toResponseList(any())).thenReturn(List.of(createProductResponse()));
        Mockito.when(productUseCase.findAll()).thenReturn(List.of(createEProduct()));

        var response = productServiceImpl.findAll();

        assertNotNull(response);
        assertEquals(1, response.size());
    }




    @Test
    void createSuccessTest() {
        Mockito.when(productMapper.toResponse(any())).thenReturn(createProductResponse());
        Mockito.when(productMapper.toEntityCreate(ProductFixture.createProductRequest())).thenReturn(ProductFixture.createEProduct());
        Mockito.when(productUseCase.create(any())).thenReturn(createEProduct());

        var response = productServiceImpl.create(ProductFixture.createProductRequest());

        assertNotNull(response);
        assertEquals(createProductResponse().getProductId(), response.getProductId());
        assertEquals(createProductResponse().getName(), response.getName());
    }

    @Test
    void updateSuccessTest() {
        Mockito.when(productMapper.toResponse(any())).thenReturn(ProductFixture.createProductResponse());
        Mockito.when(productMapper.toEntityCreate(ProductFixture.createProductRequest())).thenReturn(ProductFixture.createEProduct());
        Mockito.when(productUseCase.updateCustomer(any(), any())).thenReturn(createEProduct());

        var response = productServiceImpl.update(createEProduct().getProductId(), ProductFixture.createProductRequest());

        assertNotNull(response);
        assertEquals(createProductResponse().getProductId(), response.getProductId());
        assertEquals(createProductResponse().getName(), response.getName());
        assertEquals(createProductResponse().getDescription(), response.getDescription());
        assertEquals(createProductResponse().getImagePath(), response.getImagePath());
        assertEquals(createProductResponse().getProductValue(), response.getProductValue());


    }

    @Test
    void deleteSuccessTest() {

        Mockito.doNothing().when(productUseCase).deleteProduct(ProductFixture.createEProduct().getProductId());

        productServiceImpl.delete(ProductFixture.createProduct().getProductId());

        Mockito.verify(productUseCase, Mockito.times(1)).deleteProduct(createEProduct().getProductId());
    }
}

