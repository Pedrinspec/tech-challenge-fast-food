package com.fiap.fast_food_tc.unit.app.service;

import com.fiap.fast_food_tc.adapter.dto.orders.OrdersRequestDto;
import com.fiap.fast_food_tc.adapter.dto.orders.OrdersResponseDto;
import com.fiap.fast_food_tc.app.service.impl.OrdersServiceImpl;
import com.fiap.fast_food_tc.cross.mapper.OrdersMapper;
import com.fiap.fast_food_tc.domain.entity.EOrders;
import com.fiap.fast_food_tc.domain.usecase.OrdersUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class OrdersServiceImplTest {

    @Mock
    private OrdersMapper mapper;

    @Mock
    private OrdersUseCase ordersUseCase;

    @InjectMocks
    private OrdersServiceImpl service;

    private EOrders order;
    private OrdersResponseDto responseDto;
    private OrdersRequestDto requestDto;

    @BeforeEach
    void setUp() {
        order = EOrders.builder()
                .orderId(1)
                .orderDatetime(LocalDateTime.now())
                .statusOrder(1)
                .orderCode((short)1)
                .totalAmount(BigDecimal.ONE)
                .customerId(1)
                .build();

        responseDto = OrdersResponseDto.builder()
                .orderId(1)
                .orderDatetime(order.getOrderDatetime())
                .statusOrder(1)
                .orderCode((short)1)
                .totalAmount(BigDecimal.ONE)
                .customerId(1)
                .build();

        requestDto = OrdersRequestDto.builder()
                .orderDatetime(order.getOrderDatetime())
                .statusOrder(1)
                .totalAmount(BigDecimal.ONE)
                .customerId(1)
                .build();
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
}
