package com.fiap.fast_food_tc.unit.app.service;

import com.fiap.fast_food_tc.application.dto.orders.OrdersRequestDto;
import com.fiap.fast_food_tc.application.dto.orders.OrdersResponseDto;
import com.fiap.fast_food_tc.application.service.impl.OrdersServiceImpl;
import com.fiap.fast_food_tc.infrastructure.web.rest.mapper.OrdersMapper;
import com.fiap.fast_food_tc.domain.entity.Orders;
import com.fiap.fast_food_tc.application.usecase.OrdersUseCase;
import fixture.OrdersFixture;
import org.junit.jupiter.api.BeforeEach;
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
class OrdersPersistenceEntityServiceImplTest {

    @Mock
    private OrdersMapper mapper;

    @Mock
    private OrdersUseCase ordersUseCase;

    @InjectMocks
    private OrdersServiceImpl service;

    private Orders order;
    private OrdersResponseDto responseDto;
    private OrdersRequestDto requestDto;

    @BeforeEach
    void setUp() {
        order = OrdersFixture.createEOrders();

        responseDto = OrdersFixture.createOrdersResponseDto();
        responseDto.setOrderDatetime(order.getOrderDatetime());

        requestDto = OrdersFixture.createOrdersRequestDto();
        requestDto.setOrderDatetime(order.getOrderDatetime());
    }

    @Test
    void getAllOrdersSuccess() {
        Mockito.when(ordersUseCase.getAllOrders()).thenReturn(List.of(order));
        Mockito.when(mapper.toResponseList(any())).thenReturn(List.of(responseDto));

        var result = service.getAllOrders();

        assertEquals(1, result.size());
    }

    @Test
    void createSuccess() {
        Mockito.when(mapper.toEntityCreate(any())).thenReturn(order);
        Mockito.when(ordersUseCase.create(any())).thenReturn(order);
        Mockito.when(mapper.toResponse(any())).thenReturn(responseDto);

        var result = service.create(requestDto);

        assertEquals(responseDto, result);
    }

    @Test
    void getOrderByIdSuccess() {
        Mockito.when(ordersUseCase.getById(1)).thenReturn(order);
        Mockito.when(mapper.toResponse(order)).thenReturn(responseDto);

        var result = service.getOrderById(1);

        assertEquals(responseDto, result);
    }

    @Test
    void updateSuccess() {
        Mockito.when(mapper.toEntityCreate(any())).thenReturn(order);
        Mockito.when(ordersUseCase.update(any(), any())).thenReturn(order);
        Mockito.when(mapper.toResponse(any())).thenReturn(responseDto);

        var result = service.update(1, requestDto);

        assertEquals(responseDto, result);
    }

    @Test
    void deleteSuccess() {
        Mockito.doNothing().when(ordersUseCase).delete(1);

        service.delete(1);

        Mockito.verify(ordersUseCase, Mockito.times(1)).delete(1);
    }

}
