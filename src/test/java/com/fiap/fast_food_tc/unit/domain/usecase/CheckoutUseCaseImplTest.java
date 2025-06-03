package com.fiap.fast_food_tc.unit.domain.usecase;

import com.fiap.fast_food_tc.domain.gateway.CheckoutGateway;
import com.fiap.fast_food_tc.domain.usecase.impl.CheckoutUseCaseImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class CheckoutUseCaseImplTest {

    @Mock
    private CheckoutGateway checkoutGateway;

    @InjectMocks
    private CheckoutUseCaseImpl useCase;


    @Test
    void getPaymentLinkSuccess() {
        Mockito.when(checkoutGateway.getPaymentLink(1)).thenReturn("link");

        var result = useCase.getPaymentLink(1);

        assertEquals("link", result);
    }
}
