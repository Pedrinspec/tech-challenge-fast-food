package com.fiap.fast_food_tc.cross;

import com.fiap.fast_food_tc.adapter.db.model.Orders;
import com.fiap.fast_food_tc.adapter.dto.ordersDto.OrdersRequestDto;
import com.fiap.fast_food_tc.adapter.dto.ordersDto.OrdersResponseDto;
import com.fiap.fast_food_tc.domain.entity.EOrders;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrdersMapper {

    @Mapping(target = "orderId", ignore = true)
    @Mapping(target = "orderCode", ignore = true)
    EOrders toEntityCreate(OrdersRequestDto ordersRequestDto);

    EOrders toEntity(Orders Orders);

    Orders toModel(EOrders eOrders);

    List<OrdersResponseDto> toResponseList(List<EOrders> eOrders);

    List<EOrders> toEntityList(List<Orders> orders);

    OrdersResponseDto toResponse(EOrders eOrders);
}
