package com.fiap.fast_food_tc.infrastructure.persistence.repository;

import com.fiap.fast_food_tc.infrastructure.persistence.entity.OrderProductPersistenceEntity;
import com.fiap.fast_food_tc.infrastructure.persistence.entity.ids.OrderProductPk;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderProductRepository extends JpaRepository<OrderProductPersistenceEntity, OrderProductPk> {

    List<OrderProductPersistenceEntity> findByOrdersPersistenceEntityOrderId(Integer orderId);

}
