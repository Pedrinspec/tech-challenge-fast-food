package com.fiap.fast_food_tc.infrastructure.persistence.dataprovider;

import com.fiap.fast_food_tc.infrastructure.persistence.entity.CustomerPersistenceEntity;
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
    public CustomerPersistenceEntity create(CustomerPersistenceEntity customerPersistenceEntity) {
        return repository.save(customerPersistenceEntity);
    }

    @Override
    public CustomerPersistenceEntity findByDocumentNumber(String documentNumber) {
        return repository.findByDocumentNumber(documentNumber).orElseThrow(() -> new RuntimeException("Customer not found"));
    }

    @Override
    public CustomerPersistenceEntity findById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
    }

    @Override
    public CustomerPersistenceEntity update(CustomerPersistenceEntity customerPersistenceEntity) {
        return repository.save(customerPersistenceEntity);
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public java.util.List<CustomerPersistenceEntity> findAll() {
        return repository.findAll();
    }

}
