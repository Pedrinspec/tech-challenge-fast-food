package com.fiap.fast_food_tc.app.dto.product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {

    @NotBlank
    private String name;

    @NotNull
    private Integer quantity;

    @NotNull
    private BigDecimal productValue;

    @NotNull
    private Boolean isAvailable;

    private Integer categoryId;

    private String description;

    private String imagePath;


}
