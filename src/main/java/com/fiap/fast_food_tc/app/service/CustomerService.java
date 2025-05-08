package com.fiap.fast_food_tc.app.service;

import com.fiap.fast_food_tc.adapter.dto.customer.CustomerRequestDto;
import com.fiap.fast_food_tc.adapter.dto.customer.CustomerResponseDto;
import com.fiap.fast_food_tc.cross.CustomerMapper;
import com.fiap.fast_food_tc.domain.usecase.CustomerUseCase;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private final CustomerUseCase customerUseCase;

    private final CustomerMapper customerMapper;

    @Autowired
    public CustomerService(CustomerUseCase customerUseCase, CustomerMapper customerMapper) {
        this.customerUseCase = customerUseCase;
        this.customerMapper = customerMapper;
    }

    public CustomerResponseDto create(CustomerRequestDto user) {
        return customerMapper.entityToMessage(customerUseCase.create(customerMapper.messageToEntity(user)));
    }

    public CustomerResponseDto getByDoc(@Valid String documentNumber) {
        return customerMapper.entityToMessage(customerUseCase.getByDocumentNumber(documentNumber));
    }
}
