package com.fiap.fast_food_tc.app.service;

import com.fiap.fast_food_tc.adapter.dto.category.CategoryResponseDTO;

import java.util.List;

public interface CategoryService {
    List<CategoryResponseDTO> getAllCategories();
}
