package com.fiap.fast_food_tc.infrastructure.persistence.repository;

import com.fiap.fast_food_tc.infrastructure.persistence.entity.OrderProduct;
import com.fiap.fast_food_tc.infrastructure.persistence.entity.ids.OrderProductPk;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderProductRepository extends JpaRepository<OrderProduct, OrderProductPk> {

    List<OrderProduct> findByOrdersOrderId(Integer orderId);

}
