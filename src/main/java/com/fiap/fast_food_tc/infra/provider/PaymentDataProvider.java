package com.fiap.fast_food_tc.infra.provider;

import com.fiap.fast_food_tc.infra.db.model.Payment;
import com.fiap.fast_food_tc.infra.db.repository.PaymentRepository;
import com.fiap.fast_food_tc.domain.gateway.PaymentGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PaymentDataProvider implements PaymentGateway {

    private final PaymentRepository repository;

    @Autowired
    public PaymentDataProvider(PaymentRepository repository) {
        this.repository = repository;
    }

    @Override
    public Payment save(Payment payment) {
        return repository.save(payment);
    }
}
