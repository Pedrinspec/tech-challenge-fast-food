package com.fiap.fast_food_tc.unit.domain.usecase;

import com.fiap.fast_food_tc.cross.mapper.OrdersMapper;
import com.fiap.fast_food_tc.domain.gateway.OrdersGateway;
import com.fiap.fast_food_tc.domain.usecase.impl.OrdersUseCaseImpl;
import fixture.OrdersFixture;
import com.fiap.fast_food_tc.domain.entity.EOrders;
import com.fiap.fast_food_tc.adapter.db.model.Orders;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class OrdersUseCaseImplTest {

    @Mock
    private OrdersGateway provider;
    @Mock
    private OrdersMapper ordersMapper;
    @Mock
    private RedisTemplate<String, String> redisTemplate;
    @Mock
    private ValueOperations<String, String> valueOperations;
    @InjectMocks
    private OrdersUseCaseImpl useCase;

    @BeforeEach
    void setUp() {
        Mockito.when(redisTemplate.opsForValue()).thenReturn(valueOperations);
    }

    @Test
    void getNextOrderCodeSuccess() {
        Mockito.when(valueOperations.increment("order_code")).thenReturn(1L);

        short result = useCase.getNextOrderCode();

        assertEquals(1, result);
        verify(valueOperations, never()).set(any(), any());
    }

    @Test
    void getNextOrderCodeReset() {
        Mockito.when(valueOperations.increment("order_code")).thenReturn(1000L);

        short result = useCase.getNextOrderCode();

        assertEquals(1, result);
        verify(valueOperations).set("order_code", "1");
    }

    @Test
    void getNextOrderCodeFailure() {
        Mockito.when(valueOperations.increment("order_code")).thenReturn(null);

        assertThrows(IllegalStateException.class, () -> useCase.getNextOrderCode());
    }

    @Test
    void getAllOrdersSuccess() {
        List<Orders> models = List.of(OrdersFixture.createOrders());
        List<EOrders> entities = List.of(OrdersFixture.createEOrders());

        Mockito.when(provider.getAllOrders()).thenReturn(models);
        Mockito.when(ordersMapper.toEntityList(models)).thenReturn(entities);

        var result = useCase.getAllOrders();

        assertEquals(entities, result);
    }

    @Test
    void createSuccess() {
        EOrders entity = OrdersFixture.createEOrders();
        Orders model = OrdersFixture.createOrders();

        Mockito.when(ordersMapper.toModel(entity)).thenReturn(model);
        Mockito.when(provider.create(model)).thenReturn(model);
        Mockito.when(ordersMapper.toEntity(model)).thenReturn(entity);

        var result = useCase.create(entity);

        assertEquals(entity, result);
    }

    @Test
    void getByIdSuccess() {
        Orders model = OrdersFixture.createOrders();
        EOrders entity = OrdersFixture.createEOrders();

        Mockito.when(provider.getById(1)).thenReturn(model);
        Mockito.when(ordersMapper.toEntity(model)).thenReturn(entity);

        var result = useCase.getById(1);

        assertEquals(entity, result);
    }
}
