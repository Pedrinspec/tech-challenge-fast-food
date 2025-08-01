package com.fiap.fast_food_tc.application.service.impl;

import com.fiap.fast_food_tc.application.dto.orders.in.OrdersRequestDto;
import com.fiap.fast_food_tc.application.dto.orders.out.OrdersResponseDto;
import com.fiap.fast_food_tc.application.service.OrdersService;
import com.fiap.fast_food_tc.domain.enums.StatusOrder;
import com.fiap.fast_food_tc.infrastructure.web.mapper.OrdersMapper;
import com.fiap.fast_food_tc.application.usecase.OrdersUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
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
    public List<OrdersResponseDto> getAllOrderUnfinished() {
       return mapper.toResponseList(ordersUseCase.getAllOrders()).stream()
                .filter(order -> order.getStatusOrder() != StatusOrder.FINISHED)
                .sorted(Comparator.comparingInt((OrdersResponseDto o) -> getPriority(o.getStatusOrder()))
                        .thenComparing(OrdersResponseDto::getOrderDatetime))
                .toList();
    }

    private int getPriority(StatusOrder status) {
        return switch (status) {
            case PAYMENT_REFUSED -> 0;
            case PAYMENT_PENDING -> 1;
            case RECEIVED -> 2;
            case READY_FOR_PICKUP -> 3;
            case IN_PREPARATION -> 4;
            case FINISHED -> 5;
            case CANCELED -> 6;
        };
    }

    @Override
    public OrdersResponseDto create(OrdersRequestDto order) {
        var orderEntity = mapper.toEntityCreate(order);
        return mapper.toResponse(ordersUseCase.create(orderEntity));
    }

    @Override
    public OrdersResponseDto getOrderById(Integer id) {
        return mapper.toResponse(ordersUseCase.getById(id));
    }

    @Override
    public OrdersResponseDto update(Integer id, OrdersRequestDto dto) {
        var entity = mapper.toEntityCreate(dto);
        return mapper.toResponse(ordersUseCase.update(id, entity));
    }

    @Override
    public void delete(Integer id) {
        ordersUseCase.delete(id);
    }

    @Override
    public OrdersResponseDto updateStatus(Integer id, StatusOrder status) {
        return mapper.toResponse(ordersUseCase.updateStatusOrder(id, status));
    }
}
