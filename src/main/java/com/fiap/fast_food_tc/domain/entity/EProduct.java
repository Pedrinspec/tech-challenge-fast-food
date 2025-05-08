package com.fiap.fast_food_tc.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EProduct {

    private Integer productId;
    private String name;
    private Integer quantity;
    private Double productValue;
    private Boolean isAvailable;
    private Integer categoryId;
    private String description;
    private String imagePath;

}
