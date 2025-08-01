package com.fiap.fast_food_tc.unit.app.service;

import com.fiap.fast_food_tc.application.dto.checkout.in.CheckoutOrderRequest;
import com.fiap.fast_food_tc.application.dto.checkout.out.CheckoutResponseDto;
import com.fiap.fast_food_tc.application.service.impl.CheckoutServiceImpl;
import com.fiap.fast_food_tc.infrastructure.web.mapper.CheckoutMapper;
import com.fiap.fast_food_tc.domain.entity.Checkout;
import com.fiap.fast_food_tc.domain.entity.CheckoutOrder;
import com.fiap.fast_food_tc.application.usecase.CheckoutUseCase;
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
        CheckoutOrder.Item item = CheckoutOrder.Item.builder()
                .productId(1)
                .quantity(1)
                .build();
        CheckoutOrder entityRequest = CheckoutOrder.builder()
                .customerId(1)
                .items(List.of(item))
                .build();
        Checkout checkoutEntity = Checkout.builder()
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
