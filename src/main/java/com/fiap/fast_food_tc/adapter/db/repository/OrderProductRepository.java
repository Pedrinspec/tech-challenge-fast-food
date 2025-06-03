package com.fiap.fast_food_tc.adapter.db.repository;

import com.fiap.fast_food_tc.adapter.db.model.OrderProduct;
import com.fiap.fast_food_tc.adapter.db.model.ids.OrderProductPk;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderProductRepository extends JpaRepository<OrderProduct, OrderProductPk> {

    List<OrderProduct> findByOrdersOrderId(Integer orderId);

}
