package com.fiap.fast_food_tc.unit.domain.usecase;

import com.fiap.fast_food_tc.infrastructure.web.rest.mapper.OrderProductMapper;
import com.fiap.fast_food_tc.application.gateway.OrderProductGateway;
import com.fiap.fast_food_tc.application.usecase.impl.OrderProductUseCaseImpl;
import com.fiap.fast_food_tc.infrastructure.persistence.entity.OrderProduct;
import com.fiap.fast_food_tc.infrastructure.persistence.entity.ids.OrderProductPk;
import com.fiap.fast_food_tc.domain.entity.EOrderProduct;
import fixture.OrderProductFixture;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class OrderProductUseCaseImplTest {

    @Mock
    private OrderProductGateway gateway;
    @Mock
    private OrderProductMapper mapper;
    @InjectMocks
    private OrderProductUseCaseImpl useCase;

    @Test
    void createSuccess() {
        EOrderProduct entity = OrderProductFixture.createEOrderProduct();
        OrderProduct model = OrderProductFixture.createOrderProduct();

        Mockito.when(mapper.toModel(entity)).thenReturn(model);
        Mockito.when(gateway.create(model)).thenReturn(model);
        Mockito.when(mapper.toEntity(model)).thenReturn(entity);

        var result = useCase.create(entity);

        assertEquals(entity, result);
    }

    @Test
    void getAllSuccess() {
        List<OrderProduct> models = List.of(OrderProductFixture.createOrderProduct());
        List<EOrderProduct> entities = List.of(OrderProductFixture.createEOrderProduct());

        Mockito.when(gateway.getAll()).thenReturn(models);
        Mockito.when(mapper.toEntityList(models)).thenReturn(entities);

        var result = useCase.getAll();

        assertEquals(entities, result);
    }

    @Test
    void getByIdSuccess() {
        OrderProduct model = OrderProductFixture.createOrderProduct();
        EOrderProduct entity = OrderProductFixture.createEOrderProduct();
        OrderProductPk pk = new OrderProductPk(1,1);

        Mockito.when(gateway.getById(pk)).thenReturn(model);
        Mockito.when(mapper.toEntity(model)).thenReturn(entity);

        var result = useCase.getById(1,1);

        assertEquals(entity, result);
    }

    @Test
    void updateSuccess() {
        EOrderProduct entity = OrderProductFixture.createEOrderProduct();
        OrderProduct model = OrderProductFixture.createOrderProduct();

        Mockito.when(mapper.toModel(any())).thenReturn(model);
        Mockito.when(gateway.update(model)).thenReturn(model);
        Mockito.when(mapper.toEntity(model)).thenReturn(entity);

        var result = useCase.update(1,1, entity);

        assertEquals(entity, result);
    }

    @Test
    void deleteSuccess() {
        useCase.delete(1,1);

        Mockito.verify(gateway).delete(new OrderProductPk(1,1));
    }
}
