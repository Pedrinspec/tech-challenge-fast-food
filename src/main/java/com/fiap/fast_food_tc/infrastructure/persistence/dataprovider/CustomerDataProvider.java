package com.fiap.fast_food_tc.infrastructure.persistence.dataprovider;

import com.fiap.fast_food_tc.infrastructure.persistence.entity.Customer;
import com.fiap.fast_food_tc.infrastructure.persistence.repository.CustomerRepository;
import com.fiap.fast_food_tc.application.gateway.CustomerGateway;
import org.springframework.stereotype.Component;

@Component
public class CustomerDataProvider implements CustomerGateway {

    private final CustomerRepository repository;

    public CustomerDataProvider(CustomerRepository repository) {
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

    @Override
    public Customer findById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
    }

    @Override
    public Customer update(Customer customer) {
        return repository.save(customer);
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public java.util.List<Customer> findAll() {
        return repository.findAll();
    }

}
