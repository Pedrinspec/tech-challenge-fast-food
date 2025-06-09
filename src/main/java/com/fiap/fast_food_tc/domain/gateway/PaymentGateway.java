package com.fiap.fast_food_tc.domain.gateway;

import com.fiap.fast_food_tc.domain.entity.EPayment;
import java.util.List;

public interface PaymentGateway {

    EPayment save(EPayment payment);

    EPayment findById(Integer id);

    List<EPayment> findAll();

}
