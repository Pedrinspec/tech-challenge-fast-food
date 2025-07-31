package com.fiap.fast_food_tc.infrastructure.persistence.dataprovider;

import com.fiap.fast_food_tc.infrastructure.persistence.entity.PaymentPersistenceEntity;
import com.fiap.fast_food_tc.infrastructure.persistence.repository.PaymentRepository;
import com.fiap.fast_food_tc.application.gateway.PaymentGateway;
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
    public PaymentPersistenceEntity save(PaymentPersistenceEntity paymentPersistenceEntity) {
        return repository.save(paymentPersistenceEntity);
    }

    @Override
    public PaymentPersistenceEntity findById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Payment not found"));
    }

    @Override
    public List<PaymentPersistenceEntity> findAll() {
        return repository.findAll();
    }

    @Override
    public PaymentPersistenceEntity findByMercadoPagoId(String id) {
        return repository.findByMercadoPagoId(id)
                .orElseThrow(() -> new IllegalArgumentException("Payment not found"));
    }

    @Override
    public PaymentPersistenceEntity findByOrderId(Integer orderId) {
        return repository.findByOrdersPersistenceEntityOrderId(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Payment not found"));
    }

}
