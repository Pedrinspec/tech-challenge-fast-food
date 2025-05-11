package com.fiap.fast_food_tc.cross;

import com.fiap.fast_food_tc.adapter.db.model.Product;
import com.fiap.fast_food_tc.adapter.dto.product.ProductRequest;
import com.fiap.fast_food_tc.adapter.dto.product.ProductResponse;
import com.fiap.fast_food_tc.domain.entity.EProduct;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductResponse toResponse(EProduct product);

    @Mapping(target = "orderProducts", ignore = true)
    Product toModel(EProduct product);

    EProduct toEntity(Product product);

    @Mapping(target = "productId", ignore = true)
    EProduct toEntityCreate(ProductRequest request);

    List<ProductResponse> toResponseList(List<EProduct> list);

    List<EProduct> toEntityList(List<Product> list);

}
