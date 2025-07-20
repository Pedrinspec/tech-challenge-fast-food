package com.fiap.fast_food_tc.application.gateway;


public interface CheckoutGateway {
    String getPaymentLink(Integer orderId);

    void verifyApprovedPayment(String paymentId);
}
