package com.fiap.fast_food_tc.cross.mapper;

import com.fiap.fast_food_tc.infra.db.model.Orders;
import com.fiap.fast_food_tc.app.dto.orders.OrdersRequestDto;
import com.fiap.fast_food_tc.app.dto.orders.OrdersResponseDto;
import com.fiap.fast_food_tc.domain.entity.EOrders;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrdersMapper {

    @Mapping(target = "orderId", ignore = true)
    @Mapping(target = "orderCode", ignore = true)
    EOrders toEntityCreate(OrdersRequestDto ordersRequestDto);

    @Mapping(target = "customerId", source = "customer.customerId")
    EOrders toEntity(Orders orders);

    @Mapping(target = "customer.customerId", source = "customerId")
    @Mapping(target = "orderProducts", ignore = true)
    @Mapping(target = "payment", ignore = true)
    Orders toModel(EOrders eOrders);

    List<OrdersResponseDto> toResponseList(List<EOrders> eOrders);

    List<EOrders> toEntityList(List<Orders> orders);

    OrdersResponseDto toResponse(EOrders eOrders);
}
