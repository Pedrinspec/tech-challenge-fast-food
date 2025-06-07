package com.fiap.fast_food_tc.domain.usecase;

import com.fiap.fast_food_tc.app.dto.checkout.CheckoutOrderRequest;

public interface CheckoutUseCase {

    String getPaymentLink(Integer orderId);

    String checkoutAndCreateOrder(CheckoutOrderRequest request);

    void handleWebhook(String paymentId);
}
