package com.fiap.fast_food_tc.unit.infra.mapper;

import com.fiap.fast_food_tc.application.dto.category.out.CategoryResponseDTO;
import com.fiap.fast_food_tc.domain.entity.Category;
import com.fiap.fast_food_tc.infrastructure.persistence.entity.CategoryPersistenceEntity;
import com.fiap.fast_food_tc.infrastructure.web.mapper.CategoryMapper;
import fixture.CategoryFixture;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CategoryMapperTest {

    private final CategoryMapper mapper = Mappers.getMapper(CategoryMapper.class);

    @Test
    void toEntitySuccess() {
        CategoryPersistenceEntity categoryPersistenceEntity = CategoryFixture.createCategoryModel();

        Category result = mapper.toEntity(categoryPersistenceEntity);

        assertEquals(categoryPersistenceEntity.getCategoryId(), result.getCategoryId());
        assertEquals(categoryPersistenceEntity.getCategoryName(), result.getCategoryName());
        assertEquals(categoryPersistenceEntity.getCategoryDescription(), result.getCategoryDescription());
    }

    @Test
    void toEntityListSuccess() {
        CategoryPersistenceEntity categoryPersistenceEntity = CategoryFixture.createCategoryModel();

        List<Category> result = mapper.toEntityList(List.of(categoryPersistenceEntity));

        assertEquals(1, result.size());
        assertEquals(categoryPersistenceEntity.getCategoryId(), result.getFirst().getCategoryId());
    }

    @Test
    void toResponseDTOListSuccess() {
        Category category = CategoryFixture.createECategory();

        List<CategoryResponseDTO> result = mapper.toResponseDTOList(List.of(category));

        assertEquals(1, result.size());
        assertEquals(category.getCategoryId(), result.getFirst().getCategoryId());
    }
}
