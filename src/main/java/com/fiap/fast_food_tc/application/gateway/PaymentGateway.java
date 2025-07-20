package com.fiap.fast_food_tc.application.gateway;

import com.fiap.fast_food_tc.infrastructure.persistence.entity.PaymentPersistenceEntity;

import java.util.List;

public interface PaymentGateway {

    PaymentPersistenceEntity save(PaymentPersistenceEntity paymentPersistenceEntity);

    PaymentPersistenceEntity findById(Integer id);

    List<PaymentPersistenceEntity> findAll();

    PaymentPersistenceEntity findByOrderId(Integer orderId);
}
