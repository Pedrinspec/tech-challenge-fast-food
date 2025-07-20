package com.fiap.fast_food_tc.application.gateway;

import com.fiap.fast_food_tc.infrastructure.persistence.entity.OrderProduct;
import com.fiap.fast_food_tc.infrastructure.persistence.entity.ids.OrderProductPk;

import java.util.List;

public interface OrderProductGateway {
    OrderProduct create(OrderProduct orderProduct);
    List<OrderProduct> getAll();
    OrderProduct getById(OrderProductPk id);
    OrderProduct update(OrderProduct orderProduct);
    void delete(OrderProductPk id);
    List<OrderProduct> findByOrderId(Integer orderId);
}
