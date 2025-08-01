package com.fiap.fast_food_tc.application.service;

import com.fiap.fast_food_tc.application.dto.checkout.in.CheckoutOrderRequest;
import com.fiap.fast_food_tc.application.dto.checkout.out.CheckoutResponseDto;
import com.fiap.fast_food_tc.domain.entity.Orders;

public interface CheckoutService {

    CheckoutResponseDto checkoutAndCreateOrder(CheckoutOrderRequest request);

    Orders handleWebhook(String paymentId);
}
