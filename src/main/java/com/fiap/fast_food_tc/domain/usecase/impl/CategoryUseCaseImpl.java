package com.fiap.fast_food_tc.domain.usecase.impl;

import com.fiap.fast_food_tc.cross.mapper.CategoryMapper;
import com.fiap.fast_food_tc.domain.entity.ECategory;
import com.fiap.fast_food_tc.domain.gateway.CategoryGateway;
import com.fiap.fast_food_tc.domain.usecase.CategoryUseCase;
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
    public List<ECategory> getAllCategories() {
        return categoryMapper.toEntityList(categoryGateway.findAllCategories());
    }

    @Override
    public ECategory create(ECategory category) {
        return categoryMapper.toEntity(
                categoryGateway.createCategory(categoryMapper.toModel(category)));
    }

    @Override
    public ECategory update(Integer id, ECategory category) {
        category.setCategoryId(id);
        return categoryMapper.toEntity(
                categoryGateway.updateCategory(categoryMapper.toModel(category)));
    }
}
