package com.fiap.fast_food_tc.unit.domain.usecase;

import com.fiap.fast_food_tc.cross.mapper.OrdersMapper;
import com.fiap.fast_food_tc.domain.gateway.OrdersGateway;
import com.fiap.fast_food_tc.domain.usecase.impl.OrdersUseCaseImpl;
import fixture.OrdersFixture;
import com.fiap.fast_food_tc.domain.entity.EOrders;
import com.fiap.fast_food_tc.infra.db.model.Orders;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class OrdersUseCaseImplTest {

    @Mock
    private OrdersGateway provider;
    @Mock
    private OrdersMapper ordersMapper;
    @InjectMocks
    private OrdersUseCaseImpl useCase;

    @Test
    void getNextOrderCodeNoPrevious() {
        Mockito.when(provider.getLastOrderCode()).thenReturn(null);

        short result = useCase.getNextOrderCode();

        assertEquals(1, result);
    }

    @Test
    void getNextOrderCodeIncrement() {
        Mockito.when(provider.getLastOrderCode()).thenReturn((short)5);

        short result = useCase.getNextOrderCode();

        assertEquals(6, result);
    }

    @Test
    void getNextOrderCodeReset() {
        Mockito.when(provider.getLastOrderCode()).thenReturn((short)999);

        short result = useCase.getNextOrderCode();

        assertEquals(1, result);
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

    @Test
    void updateSuccess() {
        EOrders entity = OrdersFixture.createEOrders();
        Orders model = OrdersFixture.createOrders();

        Mockito.when(ordersMapper.toModel(any())).thenReturn(model);
        Mockito.when(provider.update(model)).thenReturn(model);
        Mockito.when(ordersMapper.toEntity(model)).thenReturn(entity);

        var result = useCase.update(1, entity);

        assertEquals(entity, result);
    }

    @Test
    void deleteSuccess() {
        useCase.delete(1);

        verify(provider).delete(1);
    }
}
