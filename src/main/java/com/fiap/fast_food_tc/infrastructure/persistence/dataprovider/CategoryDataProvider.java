package com.fiap.fast_food_tc.infrastructure.persistence.dataprovider;

import com.fiap.fast_food_tc.infrastructure.persistence.entity.Category;
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
    public List<Category> findAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category findCategoryById(Integer id) {
        return categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Category not found"));
    }

    @Override
    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public void deleteCategory(Integer id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public Category updateCategory(Category category) {
        return categoryRepository.save(category);
    }

}
