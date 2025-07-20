package com.fiap.fast_food_tc.application.usecase;

import com.fiap.fast_food_tc.domain.entity.Checkout;
import com.fiap.fast_food_tc.domain.entity.CheckoutOrder;

public interface CheckoutUseCase {

    Checkout checkoutAndCreateOrder(CheckoutOrder request);

    void handleWebhook(String paymentId);
}
