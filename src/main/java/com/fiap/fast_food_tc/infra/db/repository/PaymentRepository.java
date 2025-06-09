package com.fiap.fast_food_tc.infra.db.repository;

import com.fiap.fast_food_tc.infra.db.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {
    Optional<Payment> findByMercadoPagoId(String mercadoPagoId);
    Optional<Payment> findByOrdersOrderId(Integer orderId);
}
