package com.fiap.fast_food_tc.application.dto.orderproduct.in;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderProductRequestDto {
    private Integer orderId;
    private Integer productId;
    private Integer productQuantity;
}
