package com.fiap.fast_food_tc.domain.usecase.impl;

import com.fiap.fast_food_tc.cross.mapper.PaymentMapper;
import com.fiap.fast_food_tc.domain.entity.EPayment;
import com.fiap.fast_food_tc.domain.gateway.PaymentGateway;
import com.fiap.fast_food_tc.domain.usecase.PaymentUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PaymentUseCaseImpl implements PaymentUseCase {

    private final PaymentGateway gateway;
    private final PaymentMapper mapper;

    @Autowired
    public PaymentUseCaseImpl(PaymentGateway gateway, PaymentMapper mapper) {
        this.gateway = gateway;
        this.mapper = mapper;
    }

    @Override
    public EPayment create(EPayment payment) {
        return mapper.toEntity(gateway.save(mapper.toModel(payment)));
    }

    @Override
    public List<EPayment> findAll() {
        return mapper.toEntityList(gateway.findAll());
    }

    @Override
    public EPayment findById(Integer id) {
        return mapper.toEntity(gateway.findById(id));
    }
}