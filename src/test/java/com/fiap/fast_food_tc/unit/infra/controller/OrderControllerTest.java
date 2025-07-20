package com.fiap.fast_food_tc.unit.infra.controller;

import com.fiap.fast_food_tc.infrastructure.web.controller.OrderController;
import com.fiap.fast_food_tc.application.service.impl.OrdersServiceImpl;
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

        var response = controller.createOrder(request);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(orderResponse, response.getBody());
    }

    @Test
    void getByIdSuccess() {
        var orderResponse = OrdersFixture.createOrdersResponseDto();
        Mockito.when(ordersService.getOrderById(1)).thenReturn(orderResponse);

        var response = controller.getById(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(orderResponse, response.getBody());
    }

    @Test
    void updateSuccess() {
        var request = OrdersFixture.createOrdersRequestDto();
        var orderResponse = OrdersFixture.createOrdersResponseDto();
        Mockito.when(ordersService.update(1, request)).thenReturn(orderResponse);

        var response = controller.update(1, request);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(orderResponse, response.getBody());
    }

    @Test
    void deleteSuccess() {
        Mockito.doNothing().when(ordersService).delete(1);

        var response = controller.delete(1);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

}
