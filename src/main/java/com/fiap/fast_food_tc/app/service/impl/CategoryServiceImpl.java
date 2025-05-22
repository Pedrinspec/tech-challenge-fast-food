package com.fiap.fast_food_tc.app.service.impl;

import com.fiap.fast_food_tc.adapter.dto.category.CategoryResponseDTO;
import com.fiap.fast_food_tc.app.service.CategoryService;
import com.fiap.fast_food_tc.cross.mapper.CategoryMapper;
import com.fiap.fast_food_tc.domain.usecase.CategoryUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryUseCase categoryUseCase;

    private final CategoryMapper categoryMapper;

    @Autowired
    public CategoryServiceImpl(CategoryUseCase categoryUseCase, CategoryMapper categoryMapper) {
        this.categoryUseCase = categoryUseCase;
        this.categoryMapper = categoryMapper;
    }

    @Override
    public List<CategoryResponseDTO> getAllCategories() {
        return categoryMapper.toResponseDTOList(categoryUseCase.getAllCategories());
    }

}
