package com.fiap.fast_food_tc.app.service;

import com.fiap.fast_food_tc.app.dto.checkout.CheckoutOrderRequest;
import com.fiap.fast_food_tc.app.dto.checkout.CheckoutResponseDto;

public interface CheckoutService {

    CheckoutResponseDto checkoutAndCreateOrder(CheckoutOrderRequest request);

    void handleWebhook(String paymentId);
}
