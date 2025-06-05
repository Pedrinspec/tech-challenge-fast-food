package com.fiap.fast_food_tc.app.service;

import com.fiap.fast_food_tc.app.dto.orderproduct.OrderProductRequestDto;
import com.fiap.fast_food_tc.app.dto.orderproduct.OrderProductResponseDto;

import java.util.List;

public interface OrderProductService {
    OrderProductResponseDto create(OrderProductRequestDto dto);
    List<OrderProductResponseDto> getAll();
    OrderProductResponseDto getById(Integer orderId, Integer productId);
    OrderProductResponseDto update(Integer orderId, Integer productId, OrderProductRequestDto dto);
    void delete(Integer orderId, Integer productId);
}
