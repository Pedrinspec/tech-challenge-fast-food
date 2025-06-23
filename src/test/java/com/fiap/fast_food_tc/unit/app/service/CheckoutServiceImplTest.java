package com.fiap.fast_food_tc.unit.app.service;

import com.fiap.fast_food_tc.app.dto.checkout.CheckoutOrderRequest;
import com.fiap.fast_food_tc.app.dto.checkout.CheckoutResponseDto;
import com.fiap.fast_food_tc.app.service.impl.CheckoutServiceImpl;
import com.fiap.fast_food_tc.cross.mapper.CheckoutMapper;
import com.fiap.fast_food_tc.domain.entity.ECheckout;
import com.fiap.fast_food_tc.domain.entity.ECheckoutOrder;
import com.fiap.fast_food_tc.domain.usecase.CheckoutUseCase;
import fixture.CheckoutFixture;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CheckoutServiceImplTest {

    @Mock
    private CheckoutUseCase checkoutUseCase;

    @Mock
    private CheckoutMapper checkoutMapper;

    @InjectMocks
    private CheckoutServiceImpl service;

    @Test
    void checkoutAndCreateOrderSuccess() {
        CheckoutOrderRequest request = CheckoutFixture.createRequest();
        ECheckoutOrder.Item item = ECheckoutOrder.Item.builder()
                .productId(1)
                .quantity(1)
                .build();
        ECheckoutOrder entityRequest = ECheckoutOrder.builder()
                .customerId(1)
                .items(List.of(item))
                .build();
        ECheckout checkoutEntity = ECheckout.builder()
                .orderId(1)
                .paymentLink("link")
                .build();
        CheckoutResponseDto responseDto = CheckoutResponseDto.builder()
                .orderId(1)
                .paymentLink("link")
                .build();

        Mockito.when(checkoutMapper.toEntityRequest(request)).thenReturn(entityRequest);
        Mockito.when(checkoutUseCase.checkoutAndCreateOrder(entityRequest)).thenReturn(checkoutEntity);
        Mockito.when(checkoutMapper.toResponse(checkoutEntity)).thenReturn(responseDto);

        var result = service.checkoutAndCreateOrder(request);

        assertEquals(responseDto, result);
    }

}
