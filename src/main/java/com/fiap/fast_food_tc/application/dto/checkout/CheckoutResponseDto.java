package com.fiap.fast_food_tc.application.dto.checkout;

import com.fiap.fast_food_tc.domain.enums.StatusOrder;
import com.fiap.fast_food_tc.domain.entity.CheckoutOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CheckoutResponseDto {

    private Integer orderId;
    private String paymentLink;
    private StatusOrder statusOrder;
    private Short orderCode;
    private BigDecimal totalAmount;
    private CheckoutOrder orderRequest;

}
