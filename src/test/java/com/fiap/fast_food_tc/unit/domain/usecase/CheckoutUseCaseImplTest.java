package com.fiap.fast_food_tc.unit.domain.usecase;

import com.fiap.fast_food_tc.app.dto.checkout.CheckoutOrderRequest;
import com.fiap.fast_food_tc.domain.gateway.CheckoutGateway;
import com.fiap.fast_food_tc.domain.entity.EOrderProduct;
import com.fiap.fast_food_tc.domain.entity.EOrders;
import com.fiap.fast_food_tc.domain.entity.EProduct;
import com.fiap.fast_food_tc.domain.usecase.OrderProductUseCase;
import com.fiap.fast_food_tc.domain.usecase.OrdersUseCase;
import com.fiap.fast_food_tc.domain.usecase.ProductUseCase;
import com.fiap.fast_food_tc.domain.usecase.impl.CheckoutUseCaseImpl;
import fixture.CheckoutFixture;
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

    @Mock
    private OrdersUseCase ordersUseCase;
    @Mock
    private OrderProductUseCase orderProductUseCase;
    @Mock
    private ProductUseCase productUseCase;


    @Test
    void getPaymentLinkSuccess() {
        Mockito.when(checkoutGateway.getPaymentLink(1)).thenReturn("link");

        var result = useCase.getPaymentLink(1);

        assertEquals("link", result);
    }

    @Test
    void checkoutAndCreateOrderSuccess() {
        CheckoutOrderRequest request = CheckoutFixture.createRequest();

        Mockito.when(ordersUseCase.getNextOrderCode()).thenReturn((short)1);
        Mockito.when(ordersUseCase.create(Mockito.any(EOrders.class))).thenReturn(EOrders.builder().orderId(1).build());
        Mockito.when(productUseCase.findById(Mockito.anyInt())).thenReturn(EProduct.builder().productId(1).productValue(java.math.BigDecimal.ONE).build());
        Mockito.when(orderProductUseCase.create(Mockito.any(EOrderProduct.class))).thenReturn(EOrderProduct.builder().build());
        Mockito.when(ordersUseCase.update(Mockito.anyInt(), Mockito.any(EOrders.class))).thenReturn(EOrders.builder().orderId(1).build());
        Mockito.when(checkoutGateway.getPaymentLink(1)).thenReturn("link");

        var result = useCase.checkoutAndCreateOrder(request);

        assertEquals("link", result);
    }
}
