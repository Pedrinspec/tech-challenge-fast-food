package com.fiap.fast_food_tc.infrastructure.web.rest.mapper;

import com.fiap.fast_food_tc.application.dto.category.CategoryRequest;
import com.fiap.fast_food_tc.application.dto.category.CategoryResponseDTO;
import com.fiap.fast_food_tc.domain.entity.Category;
import com.fiap.fast_food_tc.infrastructure.persistence.entity.CategoryPersistenceEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    Category toEntity(CategoryPersistenceEntity categoryPersistenceEntity);

    List<Category> toEntityList(List<CategoryPersistenceEntity> categories);

    List<CategoryResponseDTO> toResponseDTOList(List<Category> allCategories);

    @Mapping(target = "productPersistenceEntities", ignore = true)
    CategoryPersistenceEntity toModel(Category category);

    @Mapping(target = "categoryId", ignore = true)
    Category toEntityCreate(CategoryRequest request);

    CategoryResponseDTO toResponseDTO(Category category);
}
