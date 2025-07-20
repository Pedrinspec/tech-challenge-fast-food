package com.fiap.fast_food_tc.application.dto.orderproduct;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderProductResponseDto {
    private Integer orderId;
    private Integer productId;
    private Integer productQuantity;
    private BigDecimal productTotalAmount;
}
