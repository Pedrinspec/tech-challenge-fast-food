package com.fiap.fast_food_tc.domain.enums;

import lombok.Getter;

@Getter
public enum StatusOrder {

    PAYMENT_PENDING("PAYMENT_PENDING", "Payment is pending"),
    PAYMENT_REFUSED("PAYMENT_REFUSED", "Payment has been refused"),
    RECEIVED("RECEIVED", "Order has been received"),
    IN_PREPARATION("IN_PREPARATION", "Order is being prepared"),
    READY_FOR_PICKUP("READY_FOR_PICKUP", "Order is ready for pickup"),
    FINISHED("FINISHED", "Order has been finished"),
    CANCELED("CANCELED", "Order has been canceled");

    private final String status;
    private final String description;

    StatusOrder(String status, String description) {
        this.status = status;
        this.description = description;
    }
}
