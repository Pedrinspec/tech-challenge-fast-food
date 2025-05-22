package com.fiap.fast_food_tc.domain.gateway;

import com.fiap.fast_food_tc.adapter.db.model.Category;

import java.util.List;

public interface CategoryGateway {
    List<Category> findAllCategories();

    Category findCategoryById(Integer id);

    Category createCategory(Category category);

    void deleteCategory(Integer id);

    Category updateCategory(Category category);
}
