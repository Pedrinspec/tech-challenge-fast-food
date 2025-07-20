package com.fiap.fast_food_tc.application.usecase.impl;

import com.fiap.fast_food_tc.domain.entity.Category;
import com.fiap.fast_food_tc.infrastructure.web.mapper.CategoryMapper;
import com.fiap.fast_food_tc.application.gateway.CategoryGateway;
import com.fiap.fast_food_tc.application.usecase.CategoryUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CategoryUseCaseImpl implements CategoryUseCase {

    private final CategoryGateway categoryGateway;
    private final CategoryMapper categoryMapper;

    @Autowired
    public CategoryUseCaseImpl(CategoryGateway categoryGateway, CategoryMapper categoryMapper) {
        this.categoryGateway = categoryGateway;
        this.categoryMapper = categoryMapper;
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryMapper.toEntityList(categoryGateway.findAllCategories());
    }

    @Override
    public Category create(Category category) {
        return categoryMapper.toEntity(
                categoryGateway.createCategory(categoryMapper.toModel(category)));
    }

    @Override
    public Category update(Integer id, Category category) {
        category.setCategoryId(id);
        return categoryMapper.toEntity(
                categoryGateway.updateCategory(categoryMapper.toModel(category)));
    }
}
