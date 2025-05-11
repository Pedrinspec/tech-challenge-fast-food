package com.fiap.fast_food_tc.domain.usecase.impl;

import com.fiap.fast_food_tc.cross.mapper.OrdersMapper;
import com.fiap.fast_food_tc.domain.entity.EOrders;
import com.fiap.fast_food_tc.domain.gateway.CheckoutGateway;
import com.fiap.fast_food_tc.domain.usecase.CheckoutUseCase;
import com.fiap.fast_food_tc.domain.usecase.OrdersUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CheckoutUseCaseImpl implements CheckoutUseCase {

    private final CheckoutGateway checkoutGateway;
    private final OrdersMapper ordersMapper;
    private final OrdersUseCase ordersUseCase;

    @Autowired
    public CheckoutUseCaseImpl(CheckoutGateway checkoutGateway, OrdersMapper ordersMapper, OrdersUseCase ordersUseCase) {
        this.checkoutGateway = checkoutGateway;
        this.ordersMapper = ordersMapper;
        this.ordersUseCase = ordersUseCase;
    }

    @Override
    public String getPaymentLink(Integer orderId) {
        EOrders order = ordersUseCase.getById(orderId);
        return checkoutGateway.getPaymentLink(ordersMapper.toModel(order));
    }
}
