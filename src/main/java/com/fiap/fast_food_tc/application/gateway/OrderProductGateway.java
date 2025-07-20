package com.fiap.fast_food_tc.application.gateway;

import com.fiap.fast_food_tc.infrastructure.persistence.entity.OrderProductPersistenceEntity;
import com.fiap.fast_food_tc.infrastructure.persistence.entity.ids.OrderProductPk;

import java.util.List;

public interface OrderProductGateway {
    OrderProductPersistenceEntity create(OrderProductPersistenceEntity orderProductPersistenceEntity);
    List<OrderProductPersistenceEntity> getAll();
    OrderProductPersistenceEntity getById(OrderProductPk id);
    OrderProductPersistenceEntity update(OrderProductPersistenceEntity orderProductPersistenceEntity);
    void delete(OrderProductPk id);
    List<OrderProductPersistenceEntity> findByOrderId(Integer orderId);
}
