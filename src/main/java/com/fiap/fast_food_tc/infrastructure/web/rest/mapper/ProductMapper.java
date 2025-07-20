package com.fiap.fast_food_tc.infrastructure.web.rest.mapper;

import com.fiap.fast_food_tc.application.dto.product.ProductRequest;
import com.fiap.fast_food_tc.application.dto.product.ProductResponse;
import com.fiap.fast_food_tc.domain.entity.Product;
import com.fiap.fast_food_tc.infrastructure.persistence.entity.ProductPersistenceEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductResponse toResponse(Product product);

    @Mapping(target = "orderProductPersistenceEntities", ignore = true)
    @Mapping(target = "categoryPersistenceEntity.categoryId", source = "categoryId")
    ProductPersistenceEntity toModel(Product product);

    @Mapping(source = "categoryPersistenceEntity.categoryId", target = "categoryId")
    Product toEntity(ProductPersistenceEntity productPersistenceEntity);

    @Mapping(target = "productId", ignore = true)
    Product toEntityCreate(ProductRequest request);

    List<ProductResponse> toResponseList(List<Product> list);

    List<Product> toEntityList(List<ProductPersistenceEntity> list);

}
