package com.fiap.fast_food_tc.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ECategory {

    private Integer categoryId;
    private String categoryName;
    private String categoryDescription;

}
