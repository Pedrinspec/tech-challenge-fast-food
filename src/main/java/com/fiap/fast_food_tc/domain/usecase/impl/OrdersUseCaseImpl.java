package com.fiap.fast_food_tc.domain.usecase.impl;

import com.fiap.fast_food_tc.cross.mapper.OrdersMapper;
import com.fiap.fast_food_tc.domain.entity.EOrders;
import com.fiap.fast_food_tc.domain.gateway.OrdersGateway;
import com.fiap.fast_food_tc.domain.usecase.OrdersUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrdersUseCaseImpl implements OrdersUseCase {

    private final OrdersGateway provider;

    private final OrdersMapper ordersMapper;


    @Autowired
    public OrdersUseCaseImpl(OrdersGateway dataProvider, OrdersMapper ordersMapper) {
        this.provider = dataProvider;
        this.ordersMapper = ordersMapper;
    }

    @Override
    public Short getNextOrderCode() {

        Short lastCode = provider.getLastOrderCode();
        if (lastCode == null) {
            lastCode = 0;
        }
        short next = (short) (lastCode + 1);
        if (next > 999) {
            next = 1;
        }
        return next;
    }

    @Override
    public List<EOrders> getAllOrders() {
        return ordersMapper.toEntityList(provider.getAllOrders());
    }

    @Override
    public EOrders create(EOrders orderEntity) {
        orderEntity.setOrderCode(getNextOrderCode());
        return ordersMapper.toEntity(provider.create(ordersMapper.toModel(orderEntity)));
    }

    @Override
    public EOrders getById(Integer id) {
        return ordersMapper.toEntity(provider.getById(id));
    }

    @Override
    public EOrders update(Integer id, EOrders orderEntity) {
        orderEntity.setOrderId(id);
        return ordersMapper.toEntity(provider.update(ordersMapper.toModel(orderEntity)));
    }

    @Override
    public void delete(Integer id) {
        provider.delete(id);
    }

}
