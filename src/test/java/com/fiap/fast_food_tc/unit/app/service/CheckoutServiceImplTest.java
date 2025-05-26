package com.fiap.fast_food_tc.unit.app.service;

import com.fiap.fast_food_tc.app.service.impl.CheckoutServiceImpl;
import com.fiap.fast_food_tc.domain.usecase.CheckoutUseCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CheckoutServiceImplTest {

    @Mock
    private CheckoutUseCase checkoutUseCase;

    @InjectMocks
    private CheckoutServiceImpl service;

    @Test
    void paymentPreferenceProcessSuccess() {
        Mockito.when(checkoutUseCase.getPaymentLink(1)).thenReturn("link");

        var result = service.paymentPreferenceProcess(1);

        assertEquals("link", result);
    }
}
