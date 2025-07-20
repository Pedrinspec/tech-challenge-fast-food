package com.fiap.fast_food_tc.application.service;

import com.fiap.fast_food_tc.application.dto.customer.CustomerRequestDto;
import com.fiap.fast_food_tc.application.dto.customer.CustomerResponseDto;
import jakarta.validation.Valid;

import java.util.List;

public interface CustomerService {

    CustomerResponseDto create(CustomerRequestDto user);

    CustomerResponseDto getByDoc(@Valid String documentNumber);

    CustomerResponseDto getById(Integer id);

    List<CustomerResponseDto> getAll();

    CustomerResponseDto update(Integer id, CustomerRequestDto dto);

    void delete(Integer id);
}
