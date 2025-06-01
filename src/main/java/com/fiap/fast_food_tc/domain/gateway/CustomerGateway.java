package com.fiap.fast_food_tc.domain.gateway;

import com.fiap.fast_food_tc.adapter.db.model.Customer;

import java.util.List;

public interface CustomerGateway {
    Customer create(Customer domain);

    Customer findByDocumentNumber(String documentNumber);

    Customer findById(Integer id);

    Customer update(Customer customer);

    void delete(Integer id);

    List<Customer> findAll();
}
