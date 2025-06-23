package com.fiap.fast_food_tc.cross.enums;

import lombok.Getter;

@Getter
public enum StatusOrder {

    REQUESTED("REQUESTED", "Order has been requested"),
    IN_PREPARATION("IN_PREPARATION", "Order is being prepared"),
    READY_FOR_PICKUP("READY_FOR_PICKUP", "Order is ready for pickup"),
    DELIVERED("DELIVERED", "Order has been delivered"),
    CANCELED("CANCELED", "Order has been canceled");

    private final String status;
    private final String description;

    StatusOrder(String status, String description) {
        this.status = status;
        this.description = description;
    }
}
