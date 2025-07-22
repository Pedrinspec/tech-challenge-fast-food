package com.fiap.fast_food_tc.application.dto.orders;

import com.fiap.fast_food_tc.domain.enums.StatusOrder;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateStatusOrderRequest {

    @NotNull(message = "Status order cannot be null")
    private StatusOrder newStatusOrder;

}
