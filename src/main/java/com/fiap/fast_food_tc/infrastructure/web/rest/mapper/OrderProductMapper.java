package com.fiap.fast_food_tc.infrastructure.web.rest.mapper;

import com.fiap.fast_food_tc.infrastructure.persistence.entity.OrderProduct;
import com.fiap.fast_food_tc.application.dto.orderproduct.OrderProductRequestDto;
import com.fiap.fast_food_tc.application.dto.orderproduct.OrderProductResponseDto;
import com.fiap.fast_food_tc.domain.entity.EOrderProduct;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderProductMapper {

    @Mapping(target = "orders.orderId", source = "orderId")
    @Mapping(target = "product.productId", source = "productId")
    @Mapping(target = "id", ignore = true)
    OrderProduct toModel(EOrderProduct entity);

    @Mapping(target = "orderId", source = "orders.orderId")
    @Mapping(target = "productId", source = "product.productId")
    EOrderProduct toEntity(OrderProduct model);

    @Mapping(target = "orderId", source = "orderId")
    @Mapping(target = "productId", source = "productId")
    EOrderProduct toEntityCreate(OrderProductRequestDto dto);

    OrderProductResponseDto toResponse(EOrderProduct entity);

    List<OrderProductResponseDto> toResponseList(List<EOrderProduct> list);

    List<EOrderProduct> toEntityList(List<OrderProduct> list);
}
