package com.fiap.fast_food_tc.application.dto.orders.out;

import com.fiap.fast_food_tc.domain.enums.StatusOrder;
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
    private StatusOrder statusOrder;
    private Short orderCode;
    private BigDecimal totalAmount;
    private Integer customerId;

}
