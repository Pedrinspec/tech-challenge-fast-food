package com.fiap.fast_food_tc.unit.adapter.controller;

import com.fiap.fast_food_tc.adapter.controller.CustomerController;
import com.fiap.fast_food_tc.adapter.dto.customer.CustomerRequestDto;
import com.fiap.fast_food_tc.adapter.dto.customer.CustomerResponseDto;
import com.fiap.fast_food_tc.app.service.impl.CustomerServiceImpl;
import fixture.CustomerFixture;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class CustomerControllerTest {

    @Mock
    private CustomerServiceImpl customerService;

    @InjectMocks
    private CustomerController controller;

    @Test
    void createCustomerSuccess() {
        var request = CustomerFixture.createCustomerRequestDto();
        var responseDto = CustomerFixture.createCustomerResponseDto();
        Mockito.when(customerService.create(request)).thenReturn(responseDto);

        var response = controller.createCustomer(request);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(responseDto, response.getBody());
    }

    @Test
    void getCustomerByIdSuccess() {
        var responseDto = CustomerFixture.createCustomerResponseDto();
        Mockito.when(customerService.getByDoc("11")).thenReturn(responseDto);

        var response = controller.getCustomerById("11");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(responseDto, response.getBody());
    }
}
