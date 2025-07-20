package com.fiap.fast_food_tc.application.gateway;

import com.fiap.fast_food_tc.infrastructure.persistence.entity.Category;

import java.util.List;

public interface CategoryGateway {
    List<Category> findAllCategories();

    Category findCategoryById(Integer id);

    Category createCategory(Category category);

    void deleteCategory(Integer id);

    Category updateCategory(Category category);
}
