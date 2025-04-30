package com.fiap.fast_food_tc.unit.app.service;

import com.fiap.fast_food_tc.app.service.ProductService;
import com.fiap.fast_food_tc.domain.usecase.impl.ProductUseCaseImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @InjectMocks
    private ProductService productService;
    @Mock
    private ProductUseCaseImpl productUseCase;



    @Test
    void shouldCreate() {
    }

    @Test
    void update() {
    }

    @Test
    void shouldDelete() {

        long id = 1L;
        Mockito.doNothing().when(productUseCase).delete(id);
        productService.delete(id);
        Mockito.verify(productUseCase, Mockito.times(1)).delete(id);


    }
}