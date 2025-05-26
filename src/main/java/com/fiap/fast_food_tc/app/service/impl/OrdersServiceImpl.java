package com.fiap.fast_food_tc.app.service.impl;

import com.fiap.fast_food_tc.adapter.dto.orders.OrdersRequestDto;
import com.fiap.fast_food_tc.adapter.dto.orders.OrdersResponseDto;
import com.fiap.fast_food_tc.app.service.OrdersService;
import com.fiap.fast_food_tc.cross.mapper.OrdersMapper;
import com.fiap.fast_food_tc.domain.usecase.OrdersUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdersServiceImpl implements OrdersService {

    private final OrdersMapper mapper;

    private final OrdersUseCase ordersUseCase;

    @Autowired
    public OrdersServiceImpl(OrdersMapper mapper, OrdersUseCase ordersUseCase) {
        this.mapper = mapper;
        this.ordersUseCase = ordersUseCase;
    }

    @Override
    public List<OrdersResponseDto> getAllOrders(){
        return mapper.toResponseList(ordersUseCase.getAllOrders());
    }

    @Override
    public OrdersResponseDto create(OrdersRequestDto order) {
        var orderEntity = mapper.toEntityCreate(order);
        orderEntity.setOrderCode(ordersUseCase.getNextOrderCode());
        return mapper.toResponse(ordersUseCase.create(orderEntity));
    }

    @Override
    public OrdersResponseDto getOrderById(Integer id) {
        return mapper.toResponse(ordersUseCase.getById(id));
    }
}
