package com.fiap.fast_food_tc.cross.mapper;

import com.fiap.fast_food_tc.adapter.db.model.Category;
import com.fiap.fast_food_tc.adapter.dto.category.CategoryResponseDTO;
import com.fiap.fast_food_tc.domain.entity.ECategory;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    ECategory toEntity(Category category);

    List<ECategory> toEntityList(List<Category> categories);

    List<CategoryResponseDTO> toResponseDTOList(List<ECategory> allCategories);
}
