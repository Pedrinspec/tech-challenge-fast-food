package com.fiap.fast_food_tc.domain.usecase.impl;

import com.fiap.fast_food_tc.domain.usecase.OrderUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class OrderUseCaseImpl implements OrderUseCase {

    private final RedisTemplate<String, String> redisTemplate;

    @Autowired
    public OrderUseCaseImpl(RedisTemplate<String, String> redisTemplate) {
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


}
