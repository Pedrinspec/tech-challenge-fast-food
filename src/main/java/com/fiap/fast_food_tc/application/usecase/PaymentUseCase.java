package com.fiap.fast_food_tc.application.usecase;

import com.fiap.fast_food_tc.domain.entity.EPayment;

import java.util.List;

public interface PaymentUseCase {
    EPayment create(EPayment payment);

    List<EPayment> findAll();

    EPayment findById(Integer id);

    EPayment findByOrderId(Integer orderId);
}
