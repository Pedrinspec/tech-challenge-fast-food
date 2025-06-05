package com.fiap.fast_food_tc.domain.usecase.impl;

import com.fiap.fast_food_tc.cross.mapper.OrderProductMapper;
import com.fiap.fast_food_tc.domain.entity.EOrderProduct;
import com.fiap.fast_food_tc.domain.gateway.OrderProductGateway;
import com.fiap.fast_food_tc.domain.usecase.OrderProductUseCase;
import com.fiap.fast_food_tc.infra.db.model.ids.OrderProductPk;
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
    public EOrderProduct create(EOrderProduct orderProduct) {
        return mapper.toEntity(gateway.create(mapper.toModel(orderProduct)));
    }

    @Override
    public List<EOrderProduct> getAll() {
        return mapper.toEntityList(gateway.getAll());
    }

    @Override
    public EOrderProduct getById(Integer orderId, Integer productId) {
        OrderProductPk pk = new OrderProductPk(orderId, productId);
        return mapper.toEntity(gateway.getById(pk));
    }

    @Override
    public EOrderProduct update(Integer orderId, Integer productId, EOrderProduct orderProduct) {
        orderProduct.setOrderId(orderId);
        orderProduct.setProductId(productId);
        return mapper.toEntity(gateway.update(mapper.toModel(orderProduct)));
    }

    @Override
    public void delete(Integer orderId, Integer productId) {
        gateway.delete(new OrderProductPk(orderId, productId));
    }
}
