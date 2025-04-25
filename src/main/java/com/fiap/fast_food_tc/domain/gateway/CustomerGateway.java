package com.fiap.fast_food_tc.domain.gateway;

import com.fiap.fast_food_tc.adapter.db.model.Customer;

public interface CustomerGateway {
    Customer create(Customer domain);

    Customer findByDocumentNumber(String documentNumber);
}
