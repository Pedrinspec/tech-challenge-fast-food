package com.fiap.fast_food_tc.application.service;

import com.fiap.fast_food_tc.application.dto.checkout.CheckoutOrderRequest;
import com.fiap.fast_food_tc.application.dto.checkout.CheckoutResponseDto;

public interface CheckoutService {

    CheckoutResponseDto checkoutAndCreateOrder(CheckoutOrderRequest request);

    void handleWebhook(String paymentId);
}
