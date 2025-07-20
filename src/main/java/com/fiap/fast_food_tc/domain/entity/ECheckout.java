package com.fiap.fast_food_tc.domain.entity;

import com.fiap.fast_food_tc.domain.enums.StatusOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ECheckout {

    private Integer orderId;
    private String paymentLink;
    private StatusOrder statusOrder;
    private Short orderCode;
    private BigDecimal totalAmount;
    private ECheckoutOrder orderRequest;
}
