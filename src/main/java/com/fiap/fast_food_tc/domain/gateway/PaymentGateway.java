package com.fiap.fast_food_tc.domain.gateway;

import com.fiap.fast_food_tc.infra.db.model.Payment;

public interface PaymentGateway {

    Payment save(Payment payment);

}
