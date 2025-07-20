package com.fiap.fast_food_tc.application.usecase.impl;

import com.fiap.fast_food_tc.domain.entity.OrderProduct;
import com.fiap.fast_food_tc.infrastructure.web.mapper.OrderProductMapper;
import com.fiap.fast_food_tc.application.gateway.OrderProductGateway;
import com.fiap.fast_food_tc.application.usecase.OrderProductUseCase;
import com.fiap.fast_food_tc.infrastructure.persistence.entity.ids.OrderProductPk;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderProductUseCaseImpl implements OrderProductUseCase {

    private final OrderProductGateway gateway;
    private final OrderProductMapper mapper;

    @Autowired
    public OrderProductUseCaseImpl(OrderProductGateway gateway, OrderProductMapper mapper) {
        this.gateway = gateway;
        this.mapper = mapper;
    }

    @Override
    public OrderProduct create(OrderProduct orderProduct) {
        return mapper.toEntity(gateway.create(mapper.toModel(orderProduct)));
    }

    @Override
    public List<OrderProduct> getAll() {
        return mapper.toEntityList(gateway.getAll());
    }

    @Override
    public OrderProduct getById(Integer orderId, Integer productId) {
        OrderProductPk pk = new OrderProductPk(orderId, productId);
        return mapper.toEntity(gateway.getById(pk));
    }

    @Override
    public OrderProduct update(Integer orderId, Integer productId, OrderProduct orderProduct) {
        orderProduct.setOrderId(orderId);
        orderProduct.setProductId(productId);
        return mapper.toEntity(gateway.update(mapper.toModel(orderProduct)));
    }

    @Override
    public void delete(Integer orderId, Integer productId) {
        gateway.delete(new OrderProductPk(orderId, productId));
    }
}
