package com.fiap.fast_food_tc.app.service;

import com.fiap.fast_food_tc.adapter.dto.orders.OrdersRequestDto;
import com.fiap.fast_food_tc.adapter.dto.orders.OrdersResponseDto;

import java.util.List;

public interface OrdersService {

    List<OrdersResponseDto> getAllOrders();

    OrdersResponseDto create(OrdersRequestDto order);

    OrdersResponseDto getOrderById(Integer id);

    OrdersResponseDto update(Integer id, OrdersRequestDto dto);

    void delete(Integer id);

}
