package com.fiap.fast_food_tc.adapter.db.repository;

import com.fiap.fast_food_tc.adapter.db.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {
}
