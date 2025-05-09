package com.fiap.fast_food_tc.adapter.dto.orders;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrdersResponseDto {

    private Integer orderId;
    private LocalDateTime orderDatetime;
    private Integer statusOrder;
    private Short orderCode;
    private BigDecimal totalAmount;
    private Integer customerId;

}
