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

import static fixture.ProductFixture.createProductResponse;
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
    void findAllSuccessTest(){
        //mockar tudo que é necessário injetar
        Mockito.when(productMapper.toResponseList(any())).thenReturn(List.of(createProductResponse()));
        Mockito.when(productUseCase.findAll()).thenReturn(List.of(ProductFixture.createEProduct()));

        //chamar o método da classe a ser testada
        var response = productServiceImpl.findAll();

        //verificar se o retorno é o esperado
        assertNotNull(response);
        assertEquals(1, response.size());
    }

}