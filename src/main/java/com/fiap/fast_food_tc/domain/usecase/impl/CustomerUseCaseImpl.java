package com.fiap.fast_food_tc.domain.usecase.impl;

import com.fiap.fast_food_tc.cross.mapper.CustomerMapper;
import com.fiap.fast_food_tc.domain.entity.ECustomer;
import com.fiap.fast_food_tc.domain.gateway.CustomerGateway;
import com.fiap.fast_food_tc.domain.usecase.CustomerUseCase;
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
    public ECustomer create(ECustomer eCustomer) {
        return customerMapper.toEntity(customerGateway.create(customerMapper.toDomain(eCustomer)));
    }

    @Override
    public ECustomer getByDocumentNumber(String documentNumber) {
        return customerMapper.toEntity(customerGateway.findByDocumentNumber(documentNumber));
    }

    @Override
    public void update(ECustomer user) {
        //TODO
    }

    @Override
    public void delete(Integer id) {
        //TODO
    }

    @Override
    public ECustomer getById(Integer id) {
        return null;
    }

    @Override
    public List<ECustomer> getAll() {
        return List.of();
    }


}
