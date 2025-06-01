package com.fiap.fast_food_tc.unit.domain.usecase;

import com.fiap.fast_food_tc.cross.mapper.OrdersMapper;
import com.fiap.fast_food_tc.domain.gateway.CheckoutGateway;
import com.fiap.fast_food_tc.domain.usecase.OrdersUseCase;
import com.fiap.fast_food_tc.domain.usecase.impl.CheckoutUseCaseImpl;
import fixture.OrdersFixture;
import com.fiap.fast_food_tc.domain.entity.EOrders;
import com.fiap.fast_food_tc.adapter.db.model.Orders;
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
    @Mock
    private OrdersMapper ordersMapper;
    @Mock
    private OrdersUseCase ordersUseCase;
    @InjectMocks
    private CheckoutUseCaseImpl useCase;

//    @Test
//    void getPaymentLinkSuccess() {
//        EOrders entity = OrdersFixture.createEOrders();
//        Orders model = OrdersFixture.createOrders();
//
//        Mockito.when(ordersUseCase.getById(1)).thenReturn(entity);
//        Mockito.when(ordersMapper.toModel(entity)).thenReturn(model);
//        Mockito.when(checkoutGateway.getPaymentLink(model)).thenReturn("link");
//
//        var result = useCase.getPaymentLink(1);
//
//        assertEquals("link", result);
//    }
}
