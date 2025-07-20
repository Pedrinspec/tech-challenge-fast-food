package com.fiap.fast_food_tc.application.usecase;

import com.fiap.fast_food_tc.domain.entity.ECategory;

import java.util.List;

public interface CategoryUseCase {
    List<ECategory> getAllCategories();

    ECategory create(ECategory category);

    ECategory update(Integer id, ECategory category);
}
