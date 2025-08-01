package com.fiap.fast_food_tc.unit.infra.controller;

import com.fiap.fast_food_tc.infrastructure.web.controller.ProductController;
import com.fiap.fast_food_tc.application.service.impl.ProductServiceImpl;
import fixture.ProductFixture;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class ProductControllerTest {

    @Mock
    private ProductServiceImpl productService;

    @InjectMocks
    private ProductController controller;

    @Test
    void getByIdSuccess() {
        var product = ProductFixture.createProductResponse();
        Mockito.when(productService.findById(1)).thenReturn(product);

        var response = controller.getById(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(product, response.getBody());
    }

    @Test
    void getByCategoryIdSuccess() {
        var products = List.of(ProductFixture.createProductResponse());
        Mockito.when(productService.findByCategoryId(2)).thenReturn(products);

        var response = controller.getByCategoryId(2);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(products, response.getBody());
    }

    @Test
    void getAllSuccess() {
        var products = List.of(ProductFixture.createProductResponse());
        Mockito.when(productService.findAll()).thenReturn(products);

        var response = controller.getAll();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(products, response.getBody());
    }

    @Test
    void createSuccess() {
        var request = ProductFixture.createProductRequest();
        request.setQuantity(1);
        request.setIsAvailable(true);
        var product = ProductFixture.createProductResponse();
        Mockito.when(productService.create(request)).thenReturn(product);

        var response = controller.create(request);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(product, response.getBody());
    }

    @Test
    void updateSuccess() {
        var request = ProductFixture.createProductRequest();
        request.setQuantity(1);
        request.setIsAvailable(true);
        var product = ProductFixture.createProductResponse();
        Mockito.when(productService.update(1, request)).thenReturn(product);

        var response = controller.update(1, request);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(product, response.getBody());
    }

    @Test
    void deleteSuccess() {
        var response = controller.delete(1);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        Mockito.verify(productService).delete(1);
    }
}
