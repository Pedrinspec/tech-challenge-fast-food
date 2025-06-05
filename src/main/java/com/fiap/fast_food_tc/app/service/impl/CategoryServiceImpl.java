package com.fiap.fast_food_tc.app.service.impl;

import com.fiap.fast_food_tc.app.dto.category.CategoryRequest;
import com.fiap.fast_food_tc.app.dto.category.CategoryResponseDTO;
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

    @Override
    public CategoryResponseDTO create(CategoryRequest request) {
        var entity = categoryMapper.toEntityCreate(request);
        var created = categoryUseCase.create(entity);
        return categoryMapper.toResponseDTO(created);
    }

    @Override
    public CategoryResponseDTO update(Integer id, CategoryRequest request) {
        var entity = categoryMapper.toEntityCreate(request);
        var updated = categoryUseCase.update(id, entity);
        return categoryMapper.toResponseDTO(updated);
    }

}
