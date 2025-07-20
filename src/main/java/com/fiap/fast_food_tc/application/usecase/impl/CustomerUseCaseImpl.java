package com.fiap.fast_food_tc.application.usecase.impl;

import com.fiap.fast_food_tc.domain.entity.Customer;
import com.fiap.fast_food_tc.infrastructure.web.mapper.CustomerMapper;
import com.fiap.fast_food_tc.application.gateway.CustomerGateway;
import com.fiap.fast_food_tc.application.usecase.CustomerUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomerUseCaseImpl implements CustomerUseCase {

    private final CustomerGateway customerGateway;

    private final CustomerMapper customerMapper;

    @Autowired
    public CustomerUseCaseImpl(CustomerGateway customerGateway, CustomerMapper customerMapper) {
        this.customerGateway = customerGateway;
        this.customerMapper = customerMapper;
    }

    @Override
    public Customer create(Customer customer) {
        return customerMapper.toEntity(customerGateway.create(customerMapper.toDomain(customer)));
    }

    @Override
    public Customer getByDocumentNumber(String documentNumber) {
        return customerMapper.toEntity(customerGateway.findByDocumentNumber(documentNumber));
    }

    @Override
    public Customer update(Customer user) {
        return customerMapper.toEntity(customerGateway.update(customerMapper.toDomain(user)));
    }

    @Override
    public void delete(Integer id) {
        customerGateway.delete(id);
    }

    @Override
    public Customer getById(Integer id) {
        return customerMapper.toEntity(customerGateway.findById(id));
    }

    @Override
    public List<Customer> getAll() {
        return customerMapper.toEntityList(customerGateway.findAll());
    }


}
