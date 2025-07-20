package com.fiap.fast_food_tc.infrastructure.web.rest.mapper;

import com.fiap.fast_food_tc.application.dto.orders.OrdersRequestDto;
import com.fiap.fast_food_tc.application.dto.orders.OrdersResponseDto;
import com.fiap.fast_food_tc.domain.entity.Orders;
import com.fiap.fast_food_tc.infrastructure.persistence.entity.OrdersPersistenceEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrdersMapper {

    @Mapping(target = "orderId", ignore = true)
    @Mapping(target = "orderCode", ignore = true)
    Orders toEntityCreate(OrdersRequestDto ordersRequestDto);

    @Mapping(target = "customerId", source = "customerPersistenceEntity.customerId")
    Orders toEntity(OrdersPersistenceEntity ordersPersistenceEntity);

    @Mapping(target = "customerPersistenceEntity.customerId", source = "customerId")
    @Mapping(target = "orderProductPersistenceEntities", ignore = true)
    @Mapping(target = "paymentPersistenceEntity", ignore = true)
    OrdersPersistenceEntity toModel(Orders orders);

    List<OrdersResponseDto> toResponseList(List<Orders> orders);

    List<Orders> toEntityList(List<OrdersPersistenceEntity> orders);

    OrdersResponseDto toResponse(Orders orders);
}
