package com.fiap.fast_food_tc.unit.adapter.controller;

import com.fiap.fast_food_tc.adapter.controller.ProductController;
import com.fiap.fast_food_tc.adapter.dto.product.ProductRequest;
import com.fiap.fast_food_tc.adapter.dto.product.ProductResponse;
import com.fiap.fast_food_tc.app.service.ProductService;
import fixture.ProductFixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.http.HttpStatus;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class ProductControllerTest {

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    private ProductResponse productResponse;
    private ProductRequest productRequest;

    @BeforeEach
    void setUp() {
        productResponse = ProductFixture.createProductResponse();
        productRequest = ProductFixture.createProductRequest();
    }

    @Test
    void getByIdSuccess() {
        Mockito.when(productService.findById(1)).thenReturn(productResponse);

        var response = productController.getById(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(productResponse, response.getBody());
    }

    @Test
    void getByCategoryIdSuccess() {
        Mockito.when(productService.findByCategoryId(1)).thenReturn(List.of(productResponse));

        var response = productController.getByCategoryId(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
    }

    @Test
    void getAllSuccess() {
        Mockito.when(productService.findAll()).thenReturn(List.of(productResponse));

        var response = productController.getAll();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
    }

    @Test
    void createSuccess() {
        Mockito.when(productService.create(any())).thenReturn(productResponse);

        var response = productController.create(productRequest);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(productResponse, response.getBody());
    }

    @Test
    void updateSuccess() {
        Mockito.when(productService.update(Mockito.eq(1), any())).thenReturn(productResponse);

        var response = productController.update(1, productRequest);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(productResponse, response.getBody());
    }

    @Test
    void deleteSuccess() {
        Mockito.doNothing().when(productService).delete(1);

        var response = productController.delete(1);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        Mockito.verify(productService).delete(1);
    }
}
