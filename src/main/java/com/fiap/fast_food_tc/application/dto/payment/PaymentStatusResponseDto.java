package com.fiap.fast_food_tc.application.dto.payment;


import com.fiap.fast_food_tc.domain.enums.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentStatusResponseDto {
    private Integer orderId;
    private Short orderCode;
    private PaymentStatus paymentStatus;
}