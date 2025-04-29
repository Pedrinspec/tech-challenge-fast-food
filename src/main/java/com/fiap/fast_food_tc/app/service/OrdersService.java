package com.fiap.fast_food_tc.app.service;

import com.fiap.fast_food_tc.adapter.dto.ordersDto.OrdersRequestDto;
import com.fiap.fast_food_tc.adapter.dto.ordersDto.OrdersResponseDto;
import com.fiap.fast_food_tc.cross.OrdersMapper;
import com.fiap.fast_food_tc.domain.usecase.OrdersUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdersService {

    private final OrdersMapper mapper;

    private final OrdersUseCase ordersUseCase;

    @Autowired
    public OrdersService(OrdersMapper mapper, OrdersUseCase ordersUseCase) {
        this.mapper = mapper;
        this.ordersUseCase = ordersUseCase;
    }

    public List<OrdersResponseDto> getAllOrders(){
        return mapper.toResponseList(ordersUseCase.getAllOrders());
    }

    public OrdersResponseDto create(OrdersRequestDto order) {
        var orderEntity = mapper.toEntityCreate(order);
        orderEntity.setOrderCode((short) 1);
        //orderEntity.setOrderCode(ordersUseCase.getNextOrderCode());
        return mapper.toResponse(ordersUseCase.create(orderEntity));
    }
}
