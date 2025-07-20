package com.fiap.fast_food_tc.unit.app.service;

import com.fiap.fast_food_tc.application.dto.orderproduct.OrderProductRequestDto;
import com.fiap.fast_food_tc.application.dto.orderproduct.OrderProductResponseDto;
import com.fiap.fast_food_tc.application.service.impl.OrderProductServiceImpl;
import com.fiap.fast_food_tc.infrastructure.web.mapper.OrderProductMapper;
import com.fiap.fast_food_tc.application.usecase.OrderProductUseCase;
import fixture.OrderProductFixture;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class OrderProductPersistenceEntityPersistenceEntityServiceImplTest {

    @Mock
    private OrderProductMapper mapper;

    @Mock
    private OrderProductUseCase useCase;

    @InjectMocks
    private OrderProductServiceImpl service;

    @Test
    void createSuccess() {
        OrderProductRequestDto request = OrderProductFixture.createRequest();
        OrderProductResponseDto response = OrderProductFixture.createResponse();
        Mockito.when(mapper.toEntityCreate(request)).thenReturn(OrderProductFixture.createEOrderProduct());
        Mockito.when(useCase.create(any())).thenReturn(OrderProductFixture.createEOrderProduct());
        Mockito.when(mapper.toResponse(any())).thenReturn(response);

        OrderProductResponseDto result = service.create(request);

        assertEquals(response, result);
    }

    @Test
    void getAllSuccess() {
        Mockito.when(useCase.getAll()).thenReturn(List.of(OrderProductFixture.createEOrderProduct()));
        Mockito.when(mapper.toResponseList(any())).thenReturn(List.of(OrderProductFixture.createResponse()));

        var result = service.getAll();

        assertEquals(1, result.size());
    }

    @Test
    void getByIdSuccess() {
        Mockito.when(useCase.getById(1,1)).thenReturn(OrderProductFixture.createEOrderProduct());
        Mockito.when(mapper.toResponse(any())).thenReturn(OrderProductFixture.createResponse());

        var result = service.getById(1,1);

        assertNotNull(result);
    }

    @Test
    void updateSuccess() {
        OrderProductRequestDto request = OrderProductFixture.createRequest();
        Mockito.when(mapper.toEntityCreate(request)).thenReturn(OrderProductFixture.createEOrderProduct());
        Mockito.when(useCase.update(any(), any(), any())).thenReturn(OrderProductFixture.createEOrderProduct());
        Mockito.when(mapper.toResponse(any())).thenReturn(OrderProductFixture.createResponse());

        var result = service.update(1,1, request);

        assertNotNull(result);
    }

    @Test
    void deleteSuccess() {
        Mockito.doNothing().when(useCase).delete(1,1);

        service.delete(1,1);

        Mockito.verify(useCase).delete(1,1);
    }
}
