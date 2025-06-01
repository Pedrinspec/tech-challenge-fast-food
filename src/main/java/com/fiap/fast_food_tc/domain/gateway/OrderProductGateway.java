package com.fiap.fast_food_tc.domain.gateway;

import com.fiap.fast_food_tc.adapter.db.model.OrderProduct;
import com.fiap.fast_food_tc.adapter.db.model.ids.OrderProductPk;

import java.util.List;

public interface OrderProductGateway {
    OrderProduct create(OrderProduct orderProduct);
    List<OrderProduct> getAll();
    OrderProduct getById(OrderProductPk id);
    OrderProduct update(OrderProduct orderProduct);
    void delete(OrderProductPk id);
}
