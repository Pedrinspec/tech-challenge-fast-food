package com.fiap.fast_food_tc.unit.app.controller;

import com.fiap.fast_food_tc.app.controller.CheckoutController;
import com.fiap.fast_food_tc.app.dto.checkout.CheckoutOrderRequest;
import com.fiap.fast_food_tc.app.service.CheckoutService;
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
        Mockito.when(checkoutService.paymentPreferenceProcess(1)).thenReturn("url");

        var response = controller.checkout(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("url", response.getBody());
    }

    @Test
    void checkoutFailure() {
        Mockito.when(checkoutService.paymentPreferenceProcess(1)).thenThrow(new RuntimeException("fail"));

        assertThrows(RuntimeException.class, () -> controller.checkout(1));
    }

    @Test
    void checkoutWithProductsSuccess() {
        CheckoutOrderRequest request = CheckoutFixture.createRequest();
        Mockito.when(checkoutService.checkoutAndCreateOrder(request)).thenReturn("url");

        var response = controller.checkout(request);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("url", response.getBody());
    }

}
