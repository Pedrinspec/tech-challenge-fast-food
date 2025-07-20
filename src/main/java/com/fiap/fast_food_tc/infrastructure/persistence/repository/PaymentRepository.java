package com.fiap.fast_food_tc.infrastructure.persistence.repository;

import com.fiap.fast_food_tc.infrastructure.persistence.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {
    Optional<Payment> findByMercadoPagoId(String mercadoPagoId);
    Optional<Payment> findByOrdersOrderId(Integer orderId);
}
