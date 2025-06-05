package com.fiap.fast_food_tc.unit.app.controller;

import com.fiap.fast_food_tc.app.controller.OrderProductController;
import com.fiap.fast_food_tc.app.service.OrderProductService;
import fixture.OrderProductFixture;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class OrderProductControllerTest {

    @Mock
    private OrderProductService service;

    @InjectMocks
    private OrderProductController controller;

    @Test
    void getAllSuccess() {
        var list = List.of(OrderProductFixture.createResponse());
        Mockito.when(service.getAll()).thenReturn(list);

        var response = controller.getAll();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(list, response.getBody());
    }

    @Test
    void getByIdSuccess() {
        var responseDto = OrderProductFixture.createResponse();
        Mockito.when(service.getById(1,1)).thenReturn(responseDto);

        var response = controller.getById(1,1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(responseDto, response.getBody());
    }

    @Test
    void createSuccess() {
        var request = OrderProductFixture.createRequest();
        var responseDto = OrderProductFixture.createResponse();
        Mockito.when(service.create(request)).thenReturn(responseDto);

        var response = controller.create(request);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(responseDto, response.getBody());
    }

    @Test
    void updateSuccess() {
        var request = OrderProductFixture.createRequest();
        var responseDto = OrderProductFixture.createResponse();
        Mockito.when(service.update(1,1, request)).thenReturn(responseDto);

        var response = controller.update(1,1, request);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(responseDto, response.getBody());
    }

    @Test
    void deleteSuccess() {
        Mockito.doNothing().when(service).delete(1,1);

        var response = controller.delete(1,1);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}
