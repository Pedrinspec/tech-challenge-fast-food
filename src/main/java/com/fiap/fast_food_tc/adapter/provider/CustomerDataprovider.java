package com.fiap.fast_food_tc.adapter.provider;

import com.fiap.fast_food_tc.adapter.db.model.Customer;
import com.fiap.fast_food_tc.adapter.db.repository.CustomerRepository;
import com.fiap.fast_food_tc.domain.gateway.CustomerGateway;
import org.springframework.stereotype.Component;

@Component
public class CustomerDataprovider implements CustomerGateway {

    private final CustomerRepository repository;

    public CustomerDataprovider(CustomerRepository repository) {
        this.repository = repository;
    }

    @Override
    public Customer create(Customer customer) {
        return repository.save(customer);
    }

    @Override
    public Customer findByDocumentNumber(String documentNumber) {
        return repository.findByDocumentNumber(documentNumber).orElseThrow(() -> new RuntimeException("Customer not found"));
    }

}
