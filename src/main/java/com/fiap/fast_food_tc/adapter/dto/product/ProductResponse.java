package com.fiap.fast_food_tc.adapter.dto.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse {

    private Integer productId;
    private String name;
    private Integer quantity;
    private BigDecimal productValue;
    private Boolean isAvailable;
    private Integer categoryId;
    private String description;
    private String imagePath;

}
