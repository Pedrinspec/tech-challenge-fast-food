package com.fiap.fast_food_tc.application.service;

import com.fiap.fast_food_tc.application.dto.orders.OrdersRequestDto;
import com.fiap.fast_food_tc.application.dto.orders.OrdersResponseDto;
import com.fiap.fast_food_tc.domain.enums.StatusOrder;

import java.util.List;

public interface OrdersService {

    List<OrdersResponseDto> getAllOrders();

    List<OrdersResponseDto> getAllOrderUnfinished();

    OrdersResponseDto create(OrdersRequestDto order);

    OrdersResponseDto getOrderById(Integer id);

    OrdersResponseDto update(Integer id, OrdersRequestDto dto);

    void delete(Integer id);

    OrdersResponseDto updateStatus(Integer id, StatusOrder status);
}
