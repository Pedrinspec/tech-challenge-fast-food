package com.fiap.fast_food_tc.unit.adapter.controller;

import com.fiap.fast_food_tc.adapter.controller.OrderController;
import com.fiap.fast_food_tc.adapter.dto.orders.OrdersRequestDto;
import com.fiap.fast_food_tc.adapter.dto.orders.OrdersResponseDto;
import com.fiap.fast_food_tc.app.service.impl.OrdersServiceImpl;
import fixture.OrdersFixture;
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
class OrderControllerTest {

    @Mock
    private OrdersServiceImpl ordersService;

    @InjectMocks
    private OrderController controller;

    @Test
    void getAllOrdersSuccess() {
        var orders = List.of(OrdersFixture.createOrdersResponseDto());
        Mockito.when(ordersService.getAllOrders()).thenReturn(orders);

        var response = controller.getAllOrders();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(orders, response.getBody());
    }

    @Test
    void createOrderSuccess() {
        var request = OrdersFixture.createOrdersRequestDto();
        var orderResponse = OrdersFixture.createOrdersResponseDto();
        orderResponse.setOrderDatetime(request.getOrderDatetime());
        Mockito.when(ordersService.create(request)).thenReturn(orderResponse);

        var response = controller.createCustomer(request);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(orderResponse, response.getBody());
    }
}
