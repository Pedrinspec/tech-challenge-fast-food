package com.fiap.fast_food_tc.domain.gateway;

import com.fiap.fast_food_tc.infra.db.model.OrderProduct;
import com.fiap.fast_food_tc.infra.db.model.ids.OrderProductPk;

import java.util.List;

public interface OrderProductGateway {
    OrderProduct create(OrderProduct orderProduct);
    List<OrderProduct> getAll();
    OrderProduct getById(OrderProductPk id);
    OrderProduct update(OrderProduct orderProduct);
    void delete(OrderProductPk id);
    List<OrderProduct> findByOrderId(Integer orderId);
}
