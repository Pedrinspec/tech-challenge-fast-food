package com.fiap.fast_food_tc.infra.provider;

import com.fiap.fast_food_tc.infra.db.model.Payment;
import com.fiap.fast_food_tc.infra.db.repository.PaymentRepository;
import com.fiap.fast_food_tc.domain.gateway.PaymentGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

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

    @Override
    public Payment findById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Payment not found"));
    }

    @Override
    public List<Payment> findAll() {
        return repository.findAll();
    }

    public Payment findByMercadoPagoId(String id) {
        return repository.findByMercadoPagoId(id)
                .orElseThrow(() -> new IllegalArgumentException("Payment not found"));
    }

    @Override
    public Payment findByOrderId(Integer orderId) {
        return repository.findByOrdersOrderId(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Payment not found"));
    }

}
