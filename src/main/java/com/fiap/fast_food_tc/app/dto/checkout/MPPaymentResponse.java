package com.fiap.fast_food_tc.app.dto.checkout;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MPPaymentResponse {

    private String id;
    private String status;

}
