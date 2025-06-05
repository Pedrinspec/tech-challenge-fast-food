package com.fiap.fast_food_tc.cross.mapper;

import com.fiap.fast_food_tc.infra.db.model.Product;
import com.fiap.fast_food_tc.app.dto.product.ProductRequest;
import com.fiap.fast_food_tc.app.dto.product.ProductResponse;
import com.fiap.fast_food_tc.domain.entity.EProduct;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductResponse toResponse(EProduct product);

    @Mapping(target = "orderProducts", ignore = true)
    @Mapping(target = "category.categoryId", source = "categoryId")
    Product toModel(EProduct product);

    @Mapping(source = "category.categoryId", target = "categoryId")
    EProduct toEntity(Product product);

    @Mapping(target = "productId", ignore = true)
    EProduct toEntityCreate(ProductRequest request);

    List<ProductResponse> toResponseList(List<EProduct> list);

    List<EProduct> toEntityList(List<Product> list);

}
