package com.fiap.fast_food_tc.domain.usecase;

import com.fiap.fast_food_tc.domain.entity.EProduct;
import jakarta.validation.Valid;

public interface ProductUseCase {
    EProduct create(@Valid EProduct product);
}
