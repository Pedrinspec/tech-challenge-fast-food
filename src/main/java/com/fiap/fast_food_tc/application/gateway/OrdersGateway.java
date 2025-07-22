package com.fiap.fast_food_tc.application.gateway;

import com.fiap.fast_food_tc.infrastructure.persistence.entity.OrdersPersistenceEntity;

import java.util.List;

public interface OrdersGateway {
    List<OrdersPersistenceEntity> getAllOrders();

    OrdersPersistenceEntity create(OrdersPersistenceEntity model);

    OrdersPersistenceEntity getById(Integer id);

    Short getLastOrderCode();

    OrdersPersistenceEntity update(OrdersPersistenceEntity model);

    void delete(Integer id);
}
