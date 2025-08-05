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
                .filter(order -> order.getStatusOrder() != StatusOrder.FINISHED
                        && order.getStatusOrder() != StatusOrder.CANCELED
                        && order.getStatusOrder() != StatusOrder.PAYMENT_REFUSED
                        && order.getStatusOrder() != StatusOrder.PAYMENT_PENDING)
                .sorted(Comparator.comparingInt((OrdersResponseDto o) -> o.getStatusOrder().getPriority())
                        .thenComparing(OrdersResponseDto::getOrderDatetime)
                        .thenComparing(OrdersResponseDto::getOrderDatetime))
                .toList();
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
