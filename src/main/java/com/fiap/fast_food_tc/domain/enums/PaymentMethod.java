package com.fiap.fast_food_tc.domain.enums;

import lombok.Getter;

@Getter
public enum PaymentMethod {

    MERCADO_PAGO("MERCADO_PAGO", "mercado pago"),
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
