package com.fiap.fast_food_tc.cross.enums;

import lombok.Getter;

@Getter
public enum PaymentStatus {

    PENDING("PENDING", "Pending"),
    APPROVED("APPROVED", "Approved"),
    REJECTED("REJECTED", "Rejected");

    private final String status;

    private final String description;

    PaymentStatus(String status, String description) {
        this.status = status;
        this.description = description;
    }


}
