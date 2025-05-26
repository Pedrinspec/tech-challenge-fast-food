package com.fiap.fast_food_tc.unit.adapter.controller;

import com.fiap.fast_food_tc.adapter.controller.CheckoutController;
import com.fiap.fast_food_tc.app.service.CheckoutService;
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
        Mockito.when(checkoutService.paymentPreferenceProcess(1)).thenReturn("url");

        var response = controller.checkout(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("url", response.getBody());
    }

    @Test
    void checkoutFailure() {
        Mockito.when(checkoutService.paymentPreferenceProcess(1)).thenThrow(new RuntimeException("fail"));

        var response = controller.checkout(1);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertNotNull(response.getBody());
    }
}
