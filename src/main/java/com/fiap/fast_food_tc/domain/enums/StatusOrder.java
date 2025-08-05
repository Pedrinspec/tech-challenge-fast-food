package com.fiap.fast_food_tc.domain.enums;

import lombok.Getter;

@Getter
public enum StatusOrder {

    PAYMENT_PENDING("PAYMENT_PENDING", "Payment is pending",0),
    PAYMENT_REFUSED("PAYMENT_REFUSED", "Payment has been refused", 1),
    READY_FOR_PICKUP("READY_FOR_PICKUP", "Order is ready for pickup", 2),
    IN_PREPARATION("IN_PREPARATION", "Order is being prepared", 3),
    RECEIVED("RECEIVED", "Order has been received", 4),
    FINISHED("FINISHED", "Order has been finished", 5),
    CANCELED("CANCELED", "Order has been canceled", 6);

    private final String status;
    private final String description;
    private final int priority;

    StatusOrder(String status, String description, int priority) {
        this.status = status;
        this.description = description;
        this.priority = priority;
    }
}
