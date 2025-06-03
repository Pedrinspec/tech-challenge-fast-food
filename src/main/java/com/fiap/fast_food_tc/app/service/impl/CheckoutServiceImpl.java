package com.fiap.fast_food_tc.app.service.impl;

import com.fiap.fast_food_tc.adapter.dto.checkout.CheckoutOrderRequest;
import com.fiap.fast_food_tc.app.service.CheckoutService;
import com.fiap.fast_food_tc.domain.usecase.CheckoutUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CheckoutServiceImpl implements CheckoutService {

    private final CheckoutUseCase checkoutUseCase;

    @Autowired
    public CheckoutServiceImpl(CheckoutUseCase checkoutUseCase) {
        this.checkoutUseCase = checkoutUseCase;
    }


    @Override
    public String paymentPreferenceProcess(Integer orderId) {
        return checkoutUseCase.getPaymentLink(orderId);
    }

    @Override
    public String checkoutAndCreateOrder(CheckoutOrderRequest request) {
        return checkoutUseCase.checkoutAndCreateOrder(request);
    }
}
