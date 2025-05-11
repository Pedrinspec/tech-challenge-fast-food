package com.fiap.fast_food_tc.cross.enums;

import lombok.Getter;

@Getter
public enum PaymentMethod {

    CREDIT_CARD("CREDIT_CARD", "credit card"),
    DEBIT_CARD("DEBIT_CARD", "debit card"),
    PIX("PIX", "pix"),
    CASH("CASH", "cash");

    private final String value;
    private final String description;

    PaymentMethod(String value, String description) {
        this.value = value;
        this.description = description;
    }

}
