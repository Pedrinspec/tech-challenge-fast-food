package com.fiap.fast_food_tc.app.service.impl;

import com.fiap.fast_food_tc.adapter.dto.customer.CustomerRequestDto;
import com.fiap.fast_food_tc.adapter.dto.customer.CustomerResponseDto;
import com.fiap.fast_food_tc.app.service.CustomerService;
import com.fiap.fast_food_tc.cross.mapper.CustomerMapper;
import com.fiap.fast_food_tc.domain.usecase.CustomerUseCase;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerUseCase customerUseCase;

    private final CustomerMapper customerMapper;

    @Autowired
    public CustomerServiceImpl(CustomerUseCase customerUseCase, CustomerMapper customerMapper) {
        this.customerUseCase = customerUseCase;
        this.customerMapper = customerMapper;
    }

    @Override
    public CustomerResponseDto create(CustomerRequestDto user) {
        return customerMapper.entityToMessage(customerUseCase.create(customerMapper.messageToEntity(user)));
    }

    @Override
    public CustomerResponseDto getByDoc(@Valid String documentNumber) {
        return customerMapper.entityToMessage(customerUseCase.getByDocumentNumber(documentNumber));
    }
}
