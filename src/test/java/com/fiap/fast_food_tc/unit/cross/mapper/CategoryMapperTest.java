package com.fiap.fast_food_tc.unit.cross.mapper;

import com.fiap.fast_food_tc.application.dto.category.CategoryResponseDTO;
import com.fiap.fast_food_tc.domain.entity.Category;
import com.fiap.fast_food_tc.infrastructure.web.rest.mapper.CategoryMapper;
import fixture.CategoryFixture;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CategoryMapperTest {

    private final CategoryMapper mapper = Mappers.getMapper(CategoryMapper.class);

    @Test
    void toEntitySuccess() {
        com.fiap.fast_food_tc.infrastructure.persistence.entity.Category category = CategoryFixture.createCategoryModel();

        Category result = mapper.toEntity(category);

        assertEquals(category.getCategoryId(), result.getCategoryId());
        assertEquals(category.getCategoryName(), result.getCategoryName());
        assertEquals(category.getCategoryDescription(), result.getCategoryDescription());
    }

    @Test
    void toEntityListSuccess() {
        com.fiap.fast_food_tc.infrastructure.persistence.entity.Category category = CategoryFixture.createCategoryModel();

        List<Category> result = mapper.toEntityList(List.of(category));

        assertEquals(1, result.size());
        assertEquals(category.getCategoryId(), result.getFirst().getCategoryId());
    }

    @Test
    void toResponseDTOListSuccess() {
        Category category = CategoryFixture.createECategory();

        List<CategoryResponseDTO> result = mapper.toResponseDTOList(List.of(category));

        assertEquals(1, result.size());
        assertEquals(category.getCategoryId(), result.getFirst().getCategoryId());
    }
}
