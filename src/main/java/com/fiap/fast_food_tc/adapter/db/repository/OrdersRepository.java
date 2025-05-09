package com.fiap.fast_food_tc.adapter.db.repository;

import com.fiap.fast_food_tc.adapter.db.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdersRepository  extends JpaRepository<Orders, Integer> {
}
