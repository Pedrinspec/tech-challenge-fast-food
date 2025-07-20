package com.fiap.fast_food_tc.unit.domain.usecase;

import com.fiap.fast_food_tc.infrastructure.web.rest.mapper.ProductMapper;
import com.fiap.fast_food_tc.application.gateway.ProductGateway;
import com.fiap.fast_food_tc.application.usecase.impl.ProductUseCaseImpl;
import fixture.ProductFixture;
import com.fiap.fast_food_tc.domain.entity.EProduct;
import com.fiap.fast_food_tc.infrastructure.persistence.entity.Product;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ProductUseCaseImplTest {

    @Mock
    private ProductGateway productGateway;
    @Mock
    private ProductMapper productMapper;
    @InjectMocks
    private ProductUseCaseImpl useCase;

    @Test
    void createSuccess() {
        EProduct entity = ProductFixture.createEProduct();
        Product model = ProductFixture.createProduct();

        Mockito.when(productMapper.toModel(entity)).thenReturn(model);
        Mockito.when(productGateway.create(model)).thenReturn(model);
        Mockito.when(productMapper.toEntity(model)).thenReturn(entity);

        var result = useCase.create(entity);

        assertEquals(entity, result);
    }

    @Test
    void findByCategoryIdSuccess() {
        List<Product> models = List.of(ProductFixture.createProduct());
        List<EProduct> entities = List.of(ProductFixture.createEProduct());

        Mockito.when(productGateway.findByCategoryId(1)).thenReturn(models);
        Mockito.when(productMapper.toEntityList(models)).thenReturn(entities);

        var result = useCase.findByCategoryId(1);

        assertEquals(entities, result);
    }

    @Test
    void findByIdSuccess() {
        Product model = ProductFixture.createProduct();
        EProduct entity = ProductFixture.createEProduct();

        Mockito.when(productGateway.findById(1)).thenReturn(model);
        Mockito.when(productMapper.toEntity(model)).thenReturn(entity);

        var result = useCase.findById(1);

        assertEquals(entity, result);
    }

    @Test
    void findAllSuccess() {
        List<Product> models = List.of(ProductFixture.createProduct());
        List<EProduct> entities = List.of(ProductFixture.createEProduct());

        Mockito.when(productGateway.findAll()).thenReturn(models);
        Mockito.when(productMapper.toEntityList(models)).thenReturn(entities);

        var result = useCase.findAll();

        assertEquals(entities, result);
    }

    @Test
    void updateCustomerSuccess() {
        EProduct entity = ProductFixture.createEProduct();
        Product model = ProductFixture.createProduct();

        Mockito.when(productMapper.toModel(any())).thenReturn(model);
        Mockito.when(productGateway.update(model)).thenReturn(model);
        Mockito.when(productMapper.toEntity(model)).thenReturn(entity);

        var result = useCase.updateCustomer(1, entity);

        assertEquals(entity, result);
    }

    @Test
    void deleteProductSuccess() {
        useCase.deleteProduct(1);

        verify(productGateway).delete(1);
    }
}
