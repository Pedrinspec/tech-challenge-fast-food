package com.fiap.fast_food_tc.application.usecase;

import com.fiap.fast_food_tc.domain.entity.Customer;

import java.util.List;

public interface CustomerUseCase {

    Customer create(Customer user);

    Customer update(Customer user);

    void delete(Integer id);

    Customer getById(Integer id);

    List<Customer> getAll();

    Customer getByDocumentNumber(String documentNumber);

}
