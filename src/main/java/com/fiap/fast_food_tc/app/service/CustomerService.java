package com.fiap.fast_food_tc.app.service;

import com.fiap.fast_food_tc.adapter.dto.customer.CustomerRequestDto;
import com.fiap.fast_food_tc.adapter.dto.customer.CustomerResponseDto;
import jakarta.validation.Valid;

public interface CustomerService {

    CustomerResponseDto create(CustomerRequestDto user);

    CustomerResponseDto getByDoc(@Valid String documentNumber);
}
