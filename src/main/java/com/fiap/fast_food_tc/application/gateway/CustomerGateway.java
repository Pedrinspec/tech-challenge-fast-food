package com.fiap.fast_food_tc.application.gateway;

import com.fiap.fast_food_tc.infrastructure.persistence.entity.Customer;

import java.util.List;

public interface CustomerGateway {
    Customer create(Customer domain);

    Customer findByDocumentNumber(String documentNumber);

    Customer findById(Integer id);

    Customer update(Customer customer);

    void delete(Integer id);

    List<Customer> findAll();
}
