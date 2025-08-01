package com.fiap.fast_food_tc.unit.app.usecase;

import com.fiap.fast_food_tc.application.gateway.ProductGateway;
import com.fiap.fast_food_tc.domain.entity.OrderProduct;
import com.fiap.fast_food_tc.domain.entity.Product;
import com.fiap.fast_food_tc.infrastructure.persistence.entity.OrderProductPersistenceEntity;
import com.fiap.fast_food_tc.infrastructure.persistence.entity.ProductPersistenceEntity;
import com.fiap.fast_food_tc.infrastructure.web.mapper.OrderProductMapper;
import com.fiap.fast_food_tc.application.gateway.OrderProductGateway;
import com.fiap.fast_food_tc.application.usecase.impl.OrderProductUseCaseImpl;
import com.fiap.fast_food_tc.infrastructure.persistence.entity.ids.OrderProductPk;
import com.fiap.fast_food_tc.infrastructure.web.mapper.ProductMapper;
import fixture.OrderProductFixture;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.math.BigDecimal;
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
    @Mock
    private ProductGateway productGateway;
    @Mock
    private ProductMapper productMapper;
    @InjectMocks
    private OrderProductUseCaseImpl useCase;

    @Test
    void createSuccess() {
        OrderProduct entity = OrderProductFixture.createEOrderProduct();
        ProductPersistenceEntity product = new ProductPersistenceEntity();
        product.setProductId(1);
        product.setProductValue(BigDecimal.ONE);
        product.setName("produto");
        product.setDescription("produto");
        product.setQuantity(1);
        product.setIsAvailable(true);
        OrderProductPersistenceEntity model = OrderProductFixture.createOrderProduct();

        Mockito.when(mapper.toModel(entity)).thenReturn(model);
        Mockito.when(gateway.create(model)).thenReturn(model);
        Mockito.when(mapper.toEntity(model)).thenReturn(entity);
        Mockito.when(productGateway.findById(product.getProductId())).thenReturn(product);
        Mockito.when(productMapper.toEntity(product)).thenReturn(new Product(1,"produto", 1, BigDecimal.ONE, true, 1,
         "produto",""));

        var result = useCase.create(entity);

        assertEquals(entity, result);
    }

    @Test
    void getAllSuccess() {
        List<OrderProductPersistenceEntity> models = List.of(OrderProductFixture.createOrderProduct());
        List<OrderProduct> entities = List.of(OrderProductFixture.createEOrderProduct());

        Mockito.when(gateway.getAll()).thenReturn(models);
        Mockito.when(mapper.toEntityList(models)).thenReturn(entities);

        var result = useCase.getAll();

        assertEquals(entities, result);
    }

    @Test
    void getByIdSuccess() {
        OrderProductPersistenceEntity model = OrderProductFixture.createOrderProduct();
        OrderProduct entity = OrderProductFixture.createEOrderProduct();
        OrderProductPk pk = new OrderProductPk(1,1);

        Mockito.when(gateway.getById(pk)).thenReturn(model);
        Mockito.when(mapper.toEntity(model)).thenReturn(entity);

        var result = useCase.getById(1,1);

        assertEquals(entity, result);
    }

    @Test
    void updateSuccess() {
        OrderProduct entity = OrderProductFixture.createEOrderProduct();
        OrderProductPersistenceEntity model = OrderProductFixture.createOrderProduct();

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
