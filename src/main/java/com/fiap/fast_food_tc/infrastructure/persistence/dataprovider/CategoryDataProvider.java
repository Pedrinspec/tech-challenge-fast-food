package com.fiap.fast_food_tc.infrastructure.persistence.dataprovider;

import com.fiap.fast_food_tc.infrastructure.persistence.entity.CategoryPersistenceEntity;
import com.fiap.fast_food_tc.infrastructure.persistence.repository.CategoryRepository;
import com.fiap.fast_food_tc.application.gateway.CategoryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CategoryDataProvider implements CategoryGateway {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryDataProvider(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<CategoryPersistenceEntity> findAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public CategoryPersistenceEntity findCategoryById(Integer id) {
        return categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Category not found"));
    }

    @Override
    public CategoryPersistenceEntity createCategory(CategoryPersistenceEntity categoryPersistenceEntity) {
        return categoryRepository.save(categoryPersistenceEntity);
    }

    @Override
    public void deleteCategory(Integer id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public CategoryPersistenceEntity updateCategory(CategoryPersistenceEntity categoryPersistenceEntity) {
        return categoryRepository.save(categoryPersistenceEntity);
    }

}
