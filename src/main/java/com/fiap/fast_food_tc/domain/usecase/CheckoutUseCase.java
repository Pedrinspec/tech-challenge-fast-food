package com.fiap.fast_food_tc.domain.usecase;

import com.fiap.fast_food_tc.adapter.dto.checkout.CheckoutOrderRequest;

public interface CheckoutUseCase {

    String getPaymentLink(Integer orderId);

    String checkoutAndCreateOrder(CheckoutOrderRequest request);

}
