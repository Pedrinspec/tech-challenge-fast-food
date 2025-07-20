package com.fiap.fast_food_tc.infrastructure.web.rest.mapper;

import com.fiap.fast_food_tc.application.dto.orderproduct.OrderProductRequestDto;
import com.fiap.fast_food_tc.application.dto.orderproduct.OrderProductResponseDto;
import com.fiap.fast_food_tc.domain.entity.OrderProduct;
import com.fiap.fast_food_tc.infrastructure.persistence.entity.OrderProductPersistenceEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderProductMapper {

    @Mapping(target = "ordersPersistenceEntity.orderId", source = "orderId")
    @Mapping(target = "productPersistenceEntity.productId", source = "productId")
    @Mapping(target = "id", ignore = true)
    OrderProductPersistenceEntity toModel(OrderProduct entity);

    @Mapping(target = "orderId", source = "ordersPersistenceEntity.orderId")
    @Mapping(target = "productId", source = "productPersistenceEntity.productId")
    OrderProduct toEntity(OrderProductPersistenceEntity model);

    @Mapping(target = "orderId", source = "orderId")
    @Mapping(target = "productId", source = "productId")
    OrderProduct toEntityCreate(OrderProductRequestDto dto);

    OrderProductResponseDto toResponse(OrderProduct entity);

    List<OrderProductResponseDto> toResponseList(List<OrderProduct> list);

    List<OrderProduct> toEntityList(List<OrderProductPersistenceEntity> list);
}
