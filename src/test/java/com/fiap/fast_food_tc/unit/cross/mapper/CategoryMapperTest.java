package com.fiap.fast_food_tc.unit.cross.mapper;

import com.fiap.fast_food_tc.adapter.db.model.Category;
import com.fiap.fast_food_tc.adapter.dto.category.CategoryResponseDTO;
import com.fiap.fast_food_tc.cross.mapper.CategoryMapper;
import com.fiap.fast_food_tc.domain.entity.ECategory;
import fixture.CategoryFixture;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CategoryMapperTest {

    private final CategoryMapper mapper = Mappers.getMapper(CategoryMapper.class);

    @Test
    void toEntitySuccess() {
        Category category = CategoryFixture.createCategoryModel();

        ECategory result = mapper.toEntity(category);

        assertEquals(category.getCategoryId(), result.getCategoryId());
        assertEquals(category.getCategoryName(), result.getCategoryName());
        assertEquals(category.getCategoryDescription(), result.getCategoryDescription());
    }

    @Test
    void toEntityListSuccess() {
        Category category = CategoryFixture.createCategoryModel();

        List<ECategory> result = mapper.toEntityList(List.of(category));

        assertEquals(1, result.size());
        assertEquals(category.getCategoryId(), result.get(0).getCategoryId());
    }

    @Test
    void toResponseDTOListSuccess() {
        ECategory eCategory = CategoryFixture.createECategory();

        List<CategoryResponseDTO> result = mapper.toResponseDTOList(List.of(eCategory));

        assertEquals(1, result.size());
        assertEquals(eCategory.getCategoryId(), result.get(0).getCategoryId());
    }
}
