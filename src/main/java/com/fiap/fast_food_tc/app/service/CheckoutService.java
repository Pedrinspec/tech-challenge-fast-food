package com.fiap.fast_food_tc.app.service;

import com.fiap.fast_food_tc.app.dto.checkout.CheckoutOrderRequest;

public interface CheckoutService {

    String paymentPreferenceProcess(Integer order);

    String checkoutAndCreateOrder(CheckoutOrderRequest request);

    void handleWebhook(String paymentId);
}
