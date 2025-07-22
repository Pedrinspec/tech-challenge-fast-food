package com.fiap.fast_food_tc.application.usecase;

import com.fiap.fast_food_tc.domain.entity.Category;

import java.util.List;

public interface CategoryUseCase {
    List<Category> getAllCategories();

    Category create(Category category);

    Category update(Integer id, Category category);
}
