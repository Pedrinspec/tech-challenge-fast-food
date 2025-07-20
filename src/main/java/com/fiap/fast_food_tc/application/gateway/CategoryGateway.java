package com.fiap.fast_food_tc.application.gateway;

import com.fiap.fast_food_tc.infrastructure.persistence.entity.CategoryPersistenceEntity;

import java.util.List;

public interface CategoryGateway {
    List<CategoryPersistenceEntity> findAllCategories();

    CategoryPersistenceEntity findCategoryById(Integer id);

    CategoryPersistenceEntity createCategory(CategoryPersistenceEntity categoryPersistenceEntity);

    void deleteCategory(Integer id);

    CategoryPersistenceEntity updateCategory(CategoryPersistenceEntity categoryPersistenceEntity);
}
