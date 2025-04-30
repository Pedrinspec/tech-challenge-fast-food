package com.fiap.fast_food_tc.cross;

import com.fiap.fast_food_tc.adapter.db.model.Product;
import com.fiap.fast_food_tc.adapter.dto.ProductRequestDto;
import com.fiap.fast_food_tc.adapter.dto.ProductResponseDto;
import com.fiap.fast_food_tc.domain.entity.EProduct;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    @Mapping(source = "category", target = "category")
    EProduct toEntity(Product eProduct);

    @Mapping(source = "category", target = "category")
    Product toDomain(EProduct product);


    @Mapping(source = "category", target = "category")
    EProduct messageToEntity(ProductRequestDto product);

    @Mapping(source = "category.categoryId", target = "category.categoryId")
    @Mapping(source = "category.name", target = "category.name")
    ProductResponseDto entityToMessage(EProduct product);

}
