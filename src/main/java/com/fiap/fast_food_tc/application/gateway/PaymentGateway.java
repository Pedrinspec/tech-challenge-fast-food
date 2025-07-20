package com.fiap.fast_food_tc.application.gateway;

import com.fiap.fast_food_tc.infrastructure.persistence.entity.Payment;

import java.util.List;

public interface PaymentGateway {

    Payment save(Payment payment);

    Payment findById(Integer id);

    List<Payment> findAll();

    Payment findByOrderId(Integer orderId);
}
