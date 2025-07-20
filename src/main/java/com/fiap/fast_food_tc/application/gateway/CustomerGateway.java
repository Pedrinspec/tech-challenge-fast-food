package com.fiap.fast_food_tc.application.gateway;

import com.fiap.fast_food_tc.infrastructure.persistence.entity.CustomerPersistenceEntity;

import java.util.List;

public interface CustomerGateway {
    CustomerPersistenceEntity create(CustomerPersistenceEntity domain);

    CustomerPersistenceEntity findByDocumentNumber(String documentNumber);

    CustomerPersistenceEntity findById(Integer id);

    CustomerPersistenceEntity update(CustomerPersistenceEntity customerPersistenceEntity);

    void delete(Integer id);

    List<CustomerPersistenceEntity> findAll();
}
