package com.fiap.fast_food_tc.infra.provider;

import com.fiap.fast_food_tc.domain.entity.EPayment;
import com.fiap.fast_food_tc.domain.gateway.PaymentGateway;
import com.fiap.fast_food_tc.infra.db.model.Payment;
import com.fiap.fast_food_tc.infra.db.repository.PaymentRepository;
import com.fiap.fast_food_tc.cross.mapper.PaymentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PaymentDataProvider implements PaymentGateway {

    private final PaymentRepository repository;
    private final PaymentMapper mapper;

    @Autowired
    public PaymentDataProvider(PaymentRepository repository, PaymentMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public EPayment save(EPayment payment) {
        Payment model = repository.save(mapper.toModel(payment));
        return mapper.toEntity(model);
    }

    @Override
    public EPayment findById(Integer id) {
        Payment model = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Payment not found"));
        return mapper.toEntity(model);
    }

    @Override
    public java.util.List<EPayment> findAll() {
        return mapper.toEntityList(repository.findAll());
    }

    public EPayment findByMercadoPagoId(String id) {
        Payment model = repository.findByMercadoPagoId(id)
                .orElseThrow(() -> new IllegalArgumentException("Payment not found"));
        return mapper.toEntity(model);
    }

}
