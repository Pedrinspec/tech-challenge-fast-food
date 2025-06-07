package com.fiap.fast_food_tc.domain.gateway;


public interface CheckoutGateway {
    String getPaymentLink(Integer orderId);

    void verifyApprovedPayment(String paymentId);
}
