package com.fiap.fast_food_tc.unit.adapter.controller;

import com.fiap.fast_food_tc.adapter.controller.OrderController;
import com.fiap.fast_food_tc.adapter.dto.orders.OrdersRequestDto;
import com.fiap.fast_food_tc.adapter.dto.orders.OrdersResponseDto;
import com.fiap.fast_food_tc.app.service.impl.OrdersServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class OrderControllerTest {

    @Mock
    private OrdersServiceImpl ordersService;

    @InjectMocks
    private OrderController controller;

    @Test
    void getAllOrdersSuccess() {
        var orders = List.of(OrdersResponseDto.builder().orderId(1).totalAmount(BigDecimal.ONE).orderDatetime(LocalDateTime.now()).build());
        Mockito.when(ordersService.getAllOrders()).thenReturn(orders);

        var response = controller.getAllOrders();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(orders, response.getBody());
    }

    @Test
    void createOrderSuccess() {
        var request = OrdersRequestDto.builder().totalAmount(BigDecimal.TEN).orderDatetime(LocalDateTime.now()).build();
        var orderResponse = OrdersResponseDto.builder().orderId(1).totalAmount(BigDecimal.TEN).orderDatetime(request.getOrderDatetime()).build();
        Mockito.when(ordersService.create(request)).thenReturn(orderResponse);

        var response = controller.createCustomer(request);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(orderResponse, response.getBody());
    }
}
