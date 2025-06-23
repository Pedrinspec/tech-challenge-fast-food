package com.fiap.fast_food_tc.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ECheckoutOrder {

    private Integer customerId;
    private List<ECheckoutOrder.Item> items;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Item {
        private Integer productId;
        private Integer quantity;
    }

}
