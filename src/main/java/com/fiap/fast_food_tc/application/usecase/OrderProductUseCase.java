package com.fiap.fast_food_tc.application.usecase;

import com.fiap.fast_food_tc.domain.entity.EOrderProduct;

import java.util.List;

public interface OrderProductUseCase {
    EOrderProduct create(EOrderProduct orderProduct);
    List<EOrderProduct> getAll();
    EOrderProduct getById(Integer orderId, Integer productId);
    EOrderProduct update(Integer orderId, Integer productId, EOrderProduct orderProduct);
    void delete(Integer orderId, Integer productId);
}
