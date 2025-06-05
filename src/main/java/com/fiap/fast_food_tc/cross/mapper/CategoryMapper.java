package com.fiap.fast_food_tc.cross.mapper;

import com.fiap.fast_food_tc.infra.db.model.Category;
import com.fiap.fast_food_tc.app.dto.category.CategoryRequest;
import com.fiap.fast_food_tc.app.dto.category.CategoryResponseDTO;
import com.fiap.fast_food_tc.domain.entity.ECategory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    ECategory toEntity(Category category);

    List<ECategory> toEntityList(List<Category> categories);

    List<CategoryResponseDTO> toResponseDTOList(List<ECategory> allCategories);

    @Mapping(target = "products", ignore = true)
    Category toModel(ECategory category);

    @Mapping(target = "categoryId", ignore = true)
    ECategory toEntityCreate(CategoryRequest request);

    CategoryResponseDTO toResponseDTO(ECategory category);
}
