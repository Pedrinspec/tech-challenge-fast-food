package com.fiap.fast_food_tc.domain.usecase.impl;

import com.fiap.fast_food_tc.cross.mapper.OrdersMapper;
import com.fiap.fast_food_tc.domain.gateway.CheckoutGateway;
import com.fiap.fast_food_tc.domain.usecase.CheckoutUseCase;
import com.fiap.fast_food_tc.domain.usecase.OrdersUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CheckoutUseCaseImpl implements CheckoutUseCase {

    private final CheckoutGateway checkoutGateway;

    @Autowired
    public CheckoutUseCaseImpl(CheckoutGateway checkoutGateway, OrdersMapper ordersMapper, OrdersUseCase ordersUseCase) {
        this.checkoutGateway = checkoutGateway;
    }

    @Override
    public String getPaymentLink(Integer orderId) {
        return checkoutGateway.getPaymentLink(orderId);
    }
}
