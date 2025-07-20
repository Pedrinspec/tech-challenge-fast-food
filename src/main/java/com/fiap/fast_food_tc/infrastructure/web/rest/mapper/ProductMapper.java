package com.fiap.fast_food_tc.infrastructure.web.rest.mapper;

import com.fiap.fast_food_tc.application.dto.product.ProductRequest;
import com.fiap.fast_food_tc.application.dto.product.ProductResponse;
import com.fiap.fast_food_tc.domain.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductResponse toResponse(Product product);

    @Mapping(target = "orderProducts", ignore = true)
    @Mapping(target = "category.categoryId", source = "categoryId")
    com.fiap.fast_food_tc.infrastructure.persistence.entity.Product toModel(Product product);

    @Mapping(source = "category.categoryId", target = "categoryId")
    Product toEntity(com.fiap.fast_food_tc.infrastructure.persistence.entity.Product product);

    @Mapping(target = "productId", ignore = true)
    Product toEntityCreate(ProductRequest request);

    List<ProductResponse> toResponseList(List<Product> list);

    List<Product> toEntityList(List<com.fiap.fast_food_tc.infrastructure.persistence.entity.Product> list);

}
