package com.fiap.fast_food_tc.unit.infra.provider;

import com.fiap.fast_food_tc.domain.enums.StatusOrder;
import com.fiap.fast_food_tc.infrastructure.persistence.entity.OrdersPersistenceEntity;
import com.fiap.fast_food_tc.infrastructure.persistence.repository.OrdersRepository;
import com.fiap.fast_food_tc.infrastructure.persistence.dataprovider.OrdersDataProvider;
import fixture.CustomerFixture;
import fixture.OrdersFixture;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrdersPersistenceEntityDataProviderTest {

    @Mock
    private OrdersRepository repository;

    @InjectMocks
    private OrdersDataProvider provider;

    @Test
    void getAllOrdersSuccess() {
        List<OrdersPersistenceEntity> list = List.of(OrdersFixture.createOrders());
        when(repository.findAll()).thenReturn(list);

        var result = provider.getAllOrders();

        assertEquals(list, result);
    }

    @Test
    void createSuccess() {
        OrdersPersistenceEntity order = OrdersFixture.createOrders();
        when(repository.save(order)).thenReturn(order);

        var result = provider.create(order);

        assertEquals(order, result);
    }

    @Test
    void getByIdSuccess() {
        OrdersPersistenceEntity order = OrdersFixture.createOrders();
        when(repository.findById(1)).thenReturn(Optional.of(order));

        var result = provider.getById(1);

        assertEquals(order, result);
    }

    @Test
    void updateSuccess() {
        OrdersPersistenceEntity existing = OrdersFixture.createOrders();
        when(repository.findById(existing.getOrderId())).thenReturn(Optional.of(existing));
        when(repository.save(any())).thenReturn(existing);

        OrdersPersistenceEntity update = OrdersPersistenceEntity.builder()
                .orderId(existing.getOrderId())
                .orderDatetime(LocalDateTime.now())
                .statusOrder(StatusOrder.IN_PREPARATION)
                .totalAmount(BigDecimal.TEN)
                .customerPersistenceEntity(CustomerFixture.createCustomerModel())
                .build();

        var result = provider.update(update);

        assertEquals(existing, result);
        verify(repository).save(existing);
    }

    @Test
    void deleteSuccess() {
        provider.delete(1);

        verify(repository).deleteById(1);
    }

    @Test
    void getLastOrderCodeSuccess() {
        OrdersPersistenceEntity order = OrdersFixture.createOrders();
        when(repository.findFirstByOrderByOrderCodeDesc()).thenReturn(Optional.of(order));

        Short code = provider.getLastOrderCode();

        assertEquals(order.getOrderCode(), code);
    }

    @Test
    void getLastOrderCodeEmpty() {
        when(repository.findFirstByOrderByOrderCodeDesc()).thenReturn(Optional.empty());

        Short code = provider.getLastOrderCode();

        assertEquals((short)0, code);
    }
}
