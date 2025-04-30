package com.fiap.fast_food_tc.adapter.dto;

import com.fiap.fast_food_tc.adapter.db.model.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequestDto {

    private String name;
    private Integer quantity;
    private String productValue;
    private Boolean isAvailable;
    private String description;
    private String imageUrl;
    private CategoryRequestDto category;

}
