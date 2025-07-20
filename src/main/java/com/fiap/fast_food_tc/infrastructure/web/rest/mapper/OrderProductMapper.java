package com.fiap.fast_food_tc.infrastructure.web.rest.mapper;

import com.fiap.fast_food_tc.application.dto.orderproduct.OrderProductRequestDto;
import com.fiap.fast_food_tc.application.dto.orderproduct.OrderProductResponseDto;
import com.fiap.fast_food_tc.domain.entity.OrderProduct;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderProductMapper {

    @Mapping(target = "orders.orderId", source = "orderId")
    @Mapping(target = "product.productId", source = "productId")
    @Mapping(target = "id", ignore = true)
    com.fiap.fast_food_tc.infrastructure.persistence.entity.OrderProduct toModel(OrderProduct entity);

    @Mapping(target = "orderId", source = "orders.orderId")
    @Mapping(target = "productId", source = "product.productId")
    OrderProduct toEntity(com.fiap.fast_food_tc.infrastructure.persistence.entity.OrderProduct model);

    @Mapping(target = "orderId", source = "orderId")
    @Mapping(target = "productId", source = "productId")
    OrderProduct toEntityCreate(OrderProductRequestDto dto);

    OrderProductResponseDto toResponse(OrderProduct entity);

    List<OrderProductResponseDto> toResponseList(List<OrderProduct> list);

    List<OrderProduct> toEntityList(List<com.fiap.fast_food_tc.infrastructure.persistence.entity.OrderProduct> list);
}
