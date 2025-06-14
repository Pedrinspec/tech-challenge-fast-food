package com.fiap.fast_food_tc.domain.gateway;

import com.fiap.fast_food_tc.infra.db.model.Payment;

import java.util.List;

public interface PaymentGateway {

    Payment save(Payment payment);

    Payment findById(Integer id);

    List<Payment> findAll();

    Payment findByOrderId(Integer orderId);
}
