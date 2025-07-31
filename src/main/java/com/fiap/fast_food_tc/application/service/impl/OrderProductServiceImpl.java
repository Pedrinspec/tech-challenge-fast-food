package com.fiap.fast_food_tc.application.service.impl;

import com.fiap.fast_food_tc.application.dto.orderproduct.in.OrderProductRequestDto;
import com.fiap.fast_food_tc.application.dto.orderproduct.out.OrderProductResponseDto;
import com.fiap.fast_food_tc.application.service.OrderProductService;
import com.fiap.fast_food_tc.infrastructure.web.mapper.OrderProductMapper;
import com.fiap.fast_food_tc.application.usecase.OrderProductUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderProductServiceImpl implements OrderProductService {

    private final OrderProductMapper mapper;
    private final OrderProductUseCase useCase;

    @Autowired
    public OrderProductServiceImpl(OrderProductMapper mapper, OrderProductUseCase useCase) {
        this.mapper = mapper;
        this.useCase = useCase;
    }

    @Override
    public OrderProductResponseDto create(OrderProductRequestDto dto) {
        var entity = mapper.toEntityCreate(dto);
        return mapper.toResponse(useCase.create(entity));
    }

    @Override
    public List<OrderProductResponseDto> getAll() {
        return mapper.toResponseList(useCase.getAll());
    }

    @Override
    public OrderProductResponseDto getById(Integer orderId, Integer productId) {
        return mapper.toResponse(useCase.getById(orderId, productId));
    }

    @Override
    public OrderProductResponseDto update(Integer orderId, Integer productId, OrderProductRequestDto dto) {
        var entity = mapper.toEntityCreate(dto);
        return mapper.toResponse(useCase.update(orderId, productId, entity));
    }

    @Override
    public void delete(Integer orderId, Integer productId) {
        useCase.delete(orderId, productId);
    }
}
