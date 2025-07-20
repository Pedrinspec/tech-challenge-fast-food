package com.fiap.fast_food_tc.application.service;

import com.fiap.fast_food_tc.application.dto.category.CategoryRequest;
import com.fiap.fast_food_tc.application.dto.category.CategoryResponseDTO;

import java.util.List;

public interface CategoryService {
    List<CategoryResponseDTO> getAllCategories();

    CategoryResponseDTO create(CategoryRequest request);

    CategoryResponseDTO update(Integer id, CategoryRequest request);
}
