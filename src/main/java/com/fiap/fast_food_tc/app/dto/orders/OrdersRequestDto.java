package com.fiap.fast_food_tc.app.dto.orders;

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
public class OrdersRequestDto {

    private LocalDateTime orderDatetime;
    private Integer statusOrder;
    private BigDecimal totalAmount;
    private Integer customerId;

}
