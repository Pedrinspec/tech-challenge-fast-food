package com.fiap.fast_food_tc.unit.infra.controller;

import com.fiap.fast_food_tc.infrastructure.web.controller.CheckoutController;
import com.fiap.fast_food_tc.application.dto.checkout.CheckoutOrderRequest;
import com.fiap.fast_food_tc.application.dto.checkout.CheckoutResponseDto;
import com.fiap.fast_food_tc.application.service.CheckoutService;
import fixture.CheckoutFixture;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CheckoutControllerTest {

    @Mock
    private CheckoutService checkoutService;

    @InjectMocks
    private CheckoutController controller;

    @Test
    void checkoutSuccess() {
        CheckoutOrderRequest request = CheckoutFixture.createRequest();
        CheckoutResponseDto responseDto = CheckoutResponseDto.builder()
                .orderId(1)
                .paymentLink("url")
                .build();
        Mockito.when(checkoutService.checkoutAndCreateOrder(request)).thenReturn(responseDto);

        var response = controller.checkout(request);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(responseDto, response.getBody());
    }

    @Test
    void checkoutFailure() {
        CheckoutOrderRequest request = CheckoutFixture.createRequest();
        Mockito.when(checkoutService.checkoutAndCreateOrder(request)).thenThrow(new RuntimeException("fail"));

        assertThrows(RuntimeException.class, () -> controller.checkout(request));
    }

    @Test
    void checkoutWithProductsSuccess() {
        CheckoutOrderRequest request = CheckoutFixture.createRequest();
        CheckoutResponseDto responseDto = CheckoutResponseDto.builder()
                .orderId(1)
                .paymentLink("url")
                .build();
        Mockito.when(checkoutService.checkoutAndCreateOrder(request)).thenReturn(responseDto);

        var response = controller.checkout(request);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(responseDto, response.getBody());
    }

}
