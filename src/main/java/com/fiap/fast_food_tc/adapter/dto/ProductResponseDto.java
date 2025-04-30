package com.fiap.fast_food_tc.adapter.dto;

import com.fiap.fast_food_tc.adapter.db.model.Category;
import com.fiap.fast_food_tc.domain.entity.ECategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponseDto {

    private Long productId;
    private String name;
    private String quantity;
    private String productValue;
    private String isAvailable;
    private String description;
    private String imageUrl;

    private CategoryResponseDto category;

}
