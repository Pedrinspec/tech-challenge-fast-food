package com.fiap.fast_food_tc.unit.infra.controller;

import com.fiap.fast_food_tc.infrastructure.web.rest.controller.CustomerController;
import com.fiap.fast_food_tc.application.service.impl.CustomerServiceImpl;
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
class CustomerPersistenceEntityControllerTest {

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

        var response = controller.getCustomerByDocument("11");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(responseDto, response.getBody());
    }


    @Test
    void getCustomerByIdNumericSuccess() {
        var responseDto = CustomerFixture.createCustomerResponseDto();
        Mockito.when(customerService.getById(1)).thenReturn(responseDto);

        var response = controller.getCustomerById(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(responseDto, response.getBody());
    }

    @Test
    void getAllCustomersSuccess() {
        var responses = java.util.List.of(CustomerFixture.createCustomerResponseDto());
        Mockito.when(customerService.getAll()).thenReturn(responses);

        var response = controller.getAllCustomers();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(responses, response.getBody());
    }

    @Test
    void updateCustomerSuccess() {
        var request = CustomerFixture.createCustomerRequestDto();
        var responseDto = CustomerFixture.createCustomerResponseDto();
        Mockito.when(customerService.update(1, request)).thenReturn(responseDto);

        var response = controller.updateCustomer(1, request);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(responseDto, response.getBody());
    }

    @Test
    void deleteCustomerSuccess() {
        var response = controller.deleteCustomer(1);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        Mockito.verify(customerService).delete(1);
    }

}
