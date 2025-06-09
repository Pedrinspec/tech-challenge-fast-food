package com.fiap.fast_food_tc.domain.usecase.impl;

import com.fiap.fast_food_tc.domain.entity.EPayment;
import com.fiap.fast_food_tc.domain.gateway.PaymentGateway;
import com.fiap.fast_food_tc.domain.usecase.PaymentUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PaymentUseCaseImpl implements PaymentUseCase {

    private final PaymentGateway gateway;

    @Autowired
    public PaymentUseCaseImpl(PaymentGateway gateway) {
        this.gateway = gateway;
    }

    @Override
    public EPayment create(EPayment payment) {
        return gateway.save(payment);
    }

    @Override
    public List<EPayment> findAll() {
        return gateway.findAll();
    }

    @Override
    public EPayment findById(Integer id) {
        return gateway.findById(id);
    }
}