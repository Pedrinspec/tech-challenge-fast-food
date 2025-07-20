package com.fiap.fast_food_tc.unit.app.service;

import com.fiap.fast_food_tc.application.service.impl.CategoryServiceImpl;
import com.fiap.fast_food_tc.infrastructure.web.rest.mapper.CategoryMapper;
import com.fiap.fast_food_tc.application.usecase.CategoryUseCase;
import fixture.CategoryFixture;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class CategoryPersistenceEntityServiceTest {

    @Mock
    private CategoryMapper categoryMapper;
    @Mock
    private CategoryUseCase categoryUseCase;

    @InjectMocks
    private CategoryServiceImpl categoryServiceImpl;

    @Test
    void getAllCategoriesSuccessTest(){
        Mockito.when(categoryMapper.toResponseDTOList(any())).thenReturn(List.of(CategoryFixture.createCategoryDTO()));
        Mockito.when(categoryUseCase.getAllCategories()).thenReturn(List.of(CategoryFixture.createECategory()));

        var response = categoryServiceImpl.getAllCategories();

        assertNotNull(response);
        assertEquals(1, response.size());

    }

    @Test
    void createSuccessTest() {
        Mockito.when(categoryMapper.toEntityCreate(any())).thenReturn(CategoryFixture.createECategory());
        Mockito.when(categoryUseCase.create(any())).thenReturn(CategoryFixture.createECategory());
        Mockito.when(categoryMapper.toResponseDTO(any())).thenReturn(CategoryFixture.createCategoryDTO());

        var response = categoryServiceImpl.create(CategoryFixture.createCategoryRequest());

        assertNotNull(response);
        assertEquals(CategoryFixture.createCategoryDTO().getCategoryName(), response.getCategoryName());
    }

    @Test
    void updateSuccessTest() {
        Mockito.when(categoryMapper.toEntityCreate(any())).thenReturn(CategoryFixture.createECategory());
        Mockito.when(categoryUseCase.update(any(), any())).thenReturn(CategoryFixture.createECategory());
        Mockito.when(categoryMapper.toResponseDTO(any())).thenReturn(CategoryFixture.createCategoryDTO());

        var response = categoryServiceImpl.update(1, CategoryFixture.createCategoryRequest());

        assertNotNull(response);
        assertEquals(CategoryFixture.createCategoryDTO().getCategoryDescription(), response.getCategoryDescription());
    }
}

