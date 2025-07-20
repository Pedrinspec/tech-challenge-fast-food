package com.fiap.fast_food_tc.unit.infra.provider;

import com.fiap.fast_food_tc.infrastructure.persistence.entity.OrderProductPersistenceEntity;
import com.fiap.fast_food_tc.infrastructure.persistence.entity.ids.OrderProductPk;
import com.fiap.fast_food_tc.infrastructure.persistence.repository.OrderProductRepository;
import com.fiap.fast_food_tc.infrastructure.persistence.dataprovider.OrderProductDataProvider;
import fixture.OrderProductFixture;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderProductPersistenceEntityPersistenceEntityDataProviderTest {

    @Mock
    private OrderProductRepository repository;

    @InjectMocks
    private OrderProductDataProvider provider;

    @Test
    void createSuccess() {
        OrderProductPersistenceEntity op = OrderProductFixture.createOrderProduct();
        when(repository.save(any())).thenReturn(op);

        var result = provider.create(op);

        assertEquals(op, result);
        verify(repository).save(op);
    }

    @Test
    void getAllSuccess() {
        List<OrderProductPersistenceEntity> list = List.of(OrderProductFixture.createOrderProduct());
        when(repository.findAll()).thenReturn(list);

        var result = provider.getAll();

        assertEquals(list, result);
    }

    @Test
    void getByIdSuccess() {
        OrderProductPersistenceEntity op = OrderProductFixture.createOrderProduct();
        when(repository.findById(op.getId())).thenReturn(Optional.of(op));

        var result = provider.getById(op.getId());

        assertEquals(op, result);
    }

    @Test
    void updateSuccess() {
        OrderProductPersistenceEntity existing = OrderProductFixture.createOrderProduct();
        OrderProductPk pk = existing.getId();
        when(repository.findById(pk)).thenReturn(Optional.of(existing));
        when(repository.save(any())).thenReturn(existing);

        OrderProductPersistenceEntity update = OrderProductFixture.createOrderProduct();
        update.setProductQuantity(5);

        var result = provider.update(update);

        assertEquals(existing, result);
        verify(repository).save(existing);
    }

    @Test
    void deleteSuccess() {
        OrderProductPk pk = OrderProductFixture.createOrderProduct().getId();

        provider.delete(pk);

        verify(repository).deleteById(pk);
    }
}
