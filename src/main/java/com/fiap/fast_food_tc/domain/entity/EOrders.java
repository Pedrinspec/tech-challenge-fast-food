package com.fiap.fast_food_tc.domain.entity;

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
public class EOrders {

    private Integer orderId;
    private LocalDateTime orderDatetime;
    private Integer statusOrder;
    private Short orderCode;
    private BigDecimal totalAmount;
    private Long customerId;

}
