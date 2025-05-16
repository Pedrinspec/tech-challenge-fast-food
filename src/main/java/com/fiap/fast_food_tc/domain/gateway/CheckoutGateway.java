package com.fiap.fast_food_tc.domain.gateway;

import com.fiap.fast_food_tc.adapter.db.model.Orders;

public interface CheckoutGateway {
    String getPaymentLink(Orders order);
}
