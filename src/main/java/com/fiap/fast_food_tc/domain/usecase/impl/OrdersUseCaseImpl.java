package com.fiap.fast_food_tc.domain.usecase.impl;

import com.fiap.fast_food_tc.cross.mapper.OrdersMapper;
import com.fiap.fast_food_tc.domain.entity.EOrders;
import com.fiap.fast_food_tc.domain.gateway.OrdersGateway;
import com.fiap.fast_food_tc.domain.usecase.OrdersUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrdersUseCaseImpl implements OrdersUseCase {

    private final OrdersGateway provider;

    private final OrdersMapper ordersMapper;

    private final RedisTemplate<String, String> redisTemplate;

    @Autowired
    public OrdersUseCaseImpl(OrdersGateway dataProvider, OrdersMapper ordersMapper, RedisTemplate<String, String> redisTemplate) {
        this.provider = dataProvider;
        this.ordersMapper = ordersMapper;
        this.redisTemplate = redisTemplate;
    }

    @Override
    public Short getNextOrderCode() {

        final String ORDER_CODE = "order_code";

        Long nextOrderCode = redisTemplate.opsForValue().increment(ORDER_CODE);
        if (nextOrderCode == null) {
            throw new IllegalStateException("Unable to generate order code");
        }
        if (nextOrderCode > 999){
            redisTemplate.opsForValue().set(ORDER_CODE, "1");
            nextOrderCode = 1L;
        }
        return nextOrderCode.shortValue();
    }

    @Override
    public List<EOrders> getAllOrders() {
        return ordersMapper.toEntityList(provider.getAllOrders());
    }

    @Override
    public EOrders create(EOrders orderEntity) {
        return ordersMapper.toEntity(provider.create(ordersMapper.toModel(orderEntity)));
    }

    @Override
    public EOrders getById(Integer id) {
        return ordersMapper.toEntity(provider.getById(id));
    }


}
