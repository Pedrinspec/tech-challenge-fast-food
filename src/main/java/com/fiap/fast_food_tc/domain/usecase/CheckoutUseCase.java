package com.fiap.fast_food_tc.domain.usecase;

import com.fiap.fast_food_tc.domain.entity.ECheckout;
import com.fiap.fast_food_tc.domain.entity.ECheckoutOrder;

public interface CheckoutUseCase {

    ECheckout checkoutAndCreateOrder(ECheckoutOrder request);

    void handleWebhook(String paymentId);
}
