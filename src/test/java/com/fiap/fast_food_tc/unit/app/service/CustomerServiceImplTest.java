package com.fiap.fast_food_tc.unit.app.service;

import com.fiap.fast_food_tc.application.dto.customer.CustomerRequestDto;
import com.fiap.fast_food_tc.application.dto.customer.CustomerResponseDto;
import com.fiap.fast_food_tc.application.service.impl.CustomerServiceImpl;
import com.fiap.fast_food_tc.domain.entity.Customer;
import com.fiap.fast_food_tc.infrastructure.web.rest.mapper.CustomerMapper;
import com.fiap.fast_food_tc.application.usecase.CustomerUseCase;
import fixture.CustomerFixture;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerServiceImplTest {

    @Mock
    private CustomerUseCase customerUseCase;

    @Mock
    private CustomerMapper customerMapper;

    @InjectMocks
    private CustomerServiceImpl service;

    @Test
    void shouldCreateUserSuccess() {
        CustomerRequestDto requestDto = CustomerFixture.createCustomerRequestDto();
        Customer userSalvo = CustomerFixture.createECustomer();

        when(customerMapper.messageToEntity(requestDto)).thenReturn(userSalvo);
        when(customerUseCase.create(any(Customer.class))).thenReturn(userSalvo);

        service.create(requestDto);

        verify(customerUseCase).create(any(Customer.class));
    }

    @Test
    void shouldGetByDocumentNumberSuccess() {
        Customer userSalvo = CustomerFixture.createECustomer();
        CustomerResponseDto requestDto = CustomerFixture.createCustomerResponseDto();

        when(customerUseCase.getByDocumentNumber(any())).thenReturn(userSalvo);
        when(customerMapper.entityToMessage(any())).thenReturn(requestDto);

        var response = service.getByDoc("111111");

        assertNotNull(response);
        verify(customerUseCase).getByDocumentNumber(any());
    }

    @Test
    void shouldGetByIdSuccess() {
        Customer user = CustomerFixture.createECustomer();
        CustomerResponseDto responseDto = CustomerFixture.createCustomerResponseDto();

        when(customerUseCase.getById(any())).thenReturn(user);
        when(customerMapper.entityToMessage(any())).thenReturn(responseDto);

        var response = service.getById(1);

        assertNotNull(response);
        verify(customerUseCase).getById(any());
    }

    @Test
    void shouldGetAllSuccess() {
        var entities = java.util.List.of(CustomerFixture.createECustomer());
        var dtos = java.util.List.of(CustomerFixture.createCustomerResponseDto());

        when(customerUseCase.getAll()).thenReturn(entities);
        when(customerMapper.entityToMessageList(entities)).thenReturn(dtos);

        var response = service.getAll();

        assertNotNull(response);
        verify(customerUseCase).getAll();
    }

    @Test
    void shouldUpdateSuccess() {
        CustomerRequestDto requestDto = CustomerFixture.createCustomerRequestDto();
        Customer entity = CustomerFixture.createECustomer();
        CustomerResponseDto responseDto = CustomerFixture.createCustomerResponseDto();

        when(customerMapper.messageToEntity(requestDto)).thenReturn(entity);
        when(customerUseCase.update(any())).thenReturn(entity);
        when(customerMapper.entityToMessage(entity)).thenReturn(responseDto);

        var response = service.update(1, requestDto);

        assertNotNull(response);
        verify(customerUseCase).update(any());
    }

    @Test
    void shouldDeleteSuccess() {
        service.delete(1);

        verify(customerUseCase).delete(1);
    }

}
