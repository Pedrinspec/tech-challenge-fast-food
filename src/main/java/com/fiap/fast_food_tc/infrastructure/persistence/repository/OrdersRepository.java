package com.fiap.fast_food_tc.infrastructure.persistence.repository;

import com.fiap.fast_food_tc.infrastructure.persistence.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface OrdersRepository  extends JpaRepository<Orders, Integer> {
    Optional<Orders> findFirstByOrderByOrderCodeDesc();
}
