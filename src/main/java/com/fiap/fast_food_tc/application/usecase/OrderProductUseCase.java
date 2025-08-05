package com.fiap.fast_food_tc.application.usecase;

import com.fiap.fast_food_tc.domain.entity.OrderProduct;

import java.util.List;

public interface OrderProductUseCase {
    OrderProduct create(OrderProduct orderProduct);
    List<OrderProduct> getAll();
    OrderProduct getById(Integer orderId, Integer productId);
    OrderProduct update(Integer orderId, Integer productId, OrderProduct orderProduct);
    void delete(Integer orderId, Integer productId);
    List<OrderProduct> getByOrderId(Integer orderId);
}
