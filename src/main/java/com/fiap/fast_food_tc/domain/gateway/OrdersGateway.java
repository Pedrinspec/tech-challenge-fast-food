package com.fiap.fast_food_tc.domain.gateway;

import com.fiap.fast_food_tc.infra.db.model.Orders;

import java.util.List;

public interface OrdersGateway {
    List<Orders> getAllOrders();

    Orders create(Orders model);

    Orders getById(Integer id);

    Short getLastOrderCode();

    Orders update(Orders model);

    void delete(Integer id);
}
