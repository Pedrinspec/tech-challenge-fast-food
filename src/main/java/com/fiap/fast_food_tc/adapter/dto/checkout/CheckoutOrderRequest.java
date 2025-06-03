package com.fiap.fast_food_tc.adapter.dto.checkout;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CheckoutOrderRequest {

    private Integer customerId;
    private List<Item> items;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Item {
        private Integer productId;
        private Integer quantity;
    }

}
