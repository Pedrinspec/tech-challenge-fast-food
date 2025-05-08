package com.fiap.fast_food_tc.domain.usecase;

import com.fiap.fast_food_tc.domain.entity.EProduct;
import jakarta.validation.Valid;

import java.util.List;

public interface ProductUseCase {
    EProduct create(@Valid EProduct product);

    List<EProduct> findAll();
}
