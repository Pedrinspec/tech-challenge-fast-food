package com.fiap.fast_food_tc.application.gateway;

import com.fiap.fast_food_tc.infrastructure.persistence.entity.Orders;

import java.util.List;

public interface OrdersGateway {
    List<Orders> getAllOrders();

    Orders create(Orders model);

    Orders getById(Integer id);

    Short getLastOrderCode();

    Orders update(Orders model);

    void delete(Integer id);
}
