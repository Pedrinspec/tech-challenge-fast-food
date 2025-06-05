package com.fiap.fast_food_tc.app.service.impl;

import com.fiap.fast_food_tc.app.dto.customer.CustomerRequestDto;
import com.fiap.fast_food_tc.app.dto.customer.CustomerResponseDto;
import com.fiap.fast_food_tc.app.service.CustomerService;
import com.fiap.fast_food_tc.cross.mapper.CustomerMapper;
import com.fiap.fast_food_tc.domain.usecase.CustomerUseCase;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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


    @Override
    public CustomerResponseDto getById(Integer id) {
        return customerMapper.entityToMessage(customerUseCase.getById(id));
    }

    @Override
    public List<CustomerResponseDto> getAll() {
        return customerMapper.entityToMessageList(customerUseCase.getAll());
    }

    @Override
    public CustomerResponseDto update(Integer id, CustomerRequestDto dto) {
        var entity = customerMapper.messageToEntity(dto);
        entity.setCustomerId(id);
        return customerMapper.entityToMessage(customerUseCase.update(entity));
    }

    @Override
    public void delete(Integer id) {
        customerUseCase.delete(id);
    }

}
