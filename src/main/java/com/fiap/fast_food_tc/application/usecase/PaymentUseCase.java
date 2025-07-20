package com.fiap.fast_food_tc.application.usecase;

import com.fiap.fast_food_tc.domain.entity.Payment;

import java.util.List;

public interface PaymentUseCase {
    Payment create(Payment payment);

    List<Payment> findAll();

    Payment findById(Integer id);

    Payment findByOrderId(Integer orderId);
}
