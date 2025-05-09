package com.fiap.fast_food_tc.unit.app.service;

import com.fiap.fast_food_tc.adapter.dto.customer.CustomerRequestDto;
import com.fiap.fast_food_tc.adapter.dto.customer.CustomerResponseDto;
import com.fiap.fast_food_tc.app.service.CustomerService;
import com.fiap.fast_food_tc.cross.CustomerMapper;
import com.fiap.fast_food_tc.domain.entity.ECustomer;
import com.fiap.fast_food_tc.domain.usecase.CustomerUseCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    @Mock
    private CustomerUseCase customerUseCase;

    @Mock
    private CustomerMapper customerMapper;

    @InjectMocks
    private CustomerService service;

    @Test
    void shouldCreateUserSuccess() {
        var requestDto = new CustomerRequestDto("111111", "João", "silva","joao@email.com");
        var userSalvo = new ECustomer(1, "111111", "João", "silva","joao@email.com", List.of());

        when(customerMapper.messageToEntity(requestDto)).thenReturn(userSalvo);
        when(customerUseCase.create(any(ECustomer.class))).thenReturn(userSalvo);

        service.create(requestDto);

        verify(customerUseCase).create(any(ECustomer.class));
    }

    @Test
    void shouldGetByDocumentNumberSuccess() {
        var userSalvo = new ECustomer(1, "111111", "João", "silva", "joao@email.com", List.of());
        var requestDto = new CustomerResponseDto(1, "111111", "João", "silva","joao@email.com", List.of());

        when(customerUseCase.getByDocumentNumber(any())).thenReturn(userSalvo);
        when(customerMapper.entityToMessage(any())).thenReturn(requestDto);

        var response = service.getByDoc("111111");

        assertNotNull(response);
        verify(customerUseCase).getByDocumentNumber(any());
    }

}
