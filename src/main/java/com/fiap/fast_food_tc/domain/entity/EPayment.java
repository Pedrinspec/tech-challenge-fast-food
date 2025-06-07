package com.fiap.fast_food_tc.domain.entity;

import com.fiap.fast_food_tc.cross.enums.PaymentMethod;
import com.fiap.fast_food_tc.cross.enums.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EPayment {
    private Integer paymentId;
    private PaymentMethod paymentMethod;
    private PaymentStatus paymentStatus;
    private LocalDateTime createdAt;
    private BigDecimal paymentValue;
    private String mercadoPagoId;
    private Integer customerId;
    private Integer orderId;
}
