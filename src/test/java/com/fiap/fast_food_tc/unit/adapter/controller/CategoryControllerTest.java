package com.fiap.fast_food_tc.unit.adapter.controller;

import com.fiap.fast_food_tc.adapter.controller.CategoryController;
import com.fiap.fast_food_tc.adapter.dto.category.CategoryResponseDTO;
import com.fiap.fast_food_tc.app.service.CategoryService;
import fixture.CategoryFixture;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class CategoryControllerTest {

    @Mock
    private CategoryService categoryService;

    @InjectMocks
    private CategoryController controller;

    @Test
    void getAllCategoriesSuccess() {
        var categories = List.of(CategoryFixture.createCategoryDTO());
        Mockito.when(categoryService.getAllCategories()).thenReturn(categories);

        var response = controller.getAllCategories();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(categories, response.getBody());
    }
}
