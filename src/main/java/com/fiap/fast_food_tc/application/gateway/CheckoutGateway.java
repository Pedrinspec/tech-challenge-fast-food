package com.fiap.fast_food_tc.application.gateway;


import com.fiap.fast_food_tc.application.dto.checkout.out.MPPaymentResponse;

public interface CheckoutGateway {
    String getPaymentLink(Integer orderId);

    MPPaymentResponse findMercadoPagoPaymentResponse(String paymentId);
}
