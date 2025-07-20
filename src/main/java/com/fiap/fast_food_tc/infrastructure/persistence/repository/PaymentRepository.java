package com.fiap.fast_food_tc.infrastructure.persistence.repository;

import com.fiap.fast_food_tc.infrastructure.persistence.entity.PaymentPersistenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PaymentRepository extends JpaRepository<PaymentPersistenceEntity, Integer> {
    Optional<PaymentPersistenceEntity> findByMercadoPagoId(String mercadoPagoId);
    Optional<PaymentPersistenceEntity> findByOrdersPersistenceEntityOrderId(Integer orderId);
}
