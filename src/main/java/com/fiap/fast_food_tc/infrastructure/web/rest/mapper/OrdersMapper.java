package com.fiap.fast_food_tc.infrastructure.web.rest.mapper;

import com.fiap.fast_food_tc.application.dto.orders.OrdersRequestDto;
import com.fiap.fast_food_tc.application.dto.orders.OrdersResponseDto;
import com.fiap.fast_food_tc.domain.entity.Orders;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrdersMapper {

    @Mapping(target = "orderId", ignore = true)
    @Mapping(target = "orderCode", ignore = true)
    Orders toEntityCreate(OrdersRequestDto ordersRequestDto);

    @Mapping(target = "customerId", source = "customer.customerId")
    Orders toEntity(com.fiap.fast_food_tc.infrastructure.persistence.entity.Orders orders);

    @Mapping(target = "customer.customerId", source = "customerId")
    @Mapping(target = "orderProducts", ignore = true)
    @Mapping(target = "payment", ignore = true)
    com.fiap.fast_food_tc.infrastructure.persistence.entity.Orders toModel(Orders orders);

    List<OrdersResponseDto> toResponseList(List<Orders> orders);

    List<Orders> toEntityList(List<com.fiap.fast_food_tc.infrastructure.persistence.entity.Orders> orders);

    OrdersResponseDto toResponse(Orders orders);
}
