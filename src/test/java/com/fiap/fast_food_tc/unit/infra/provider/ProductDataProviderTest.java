package com.fiap.fast_food_tc.unit.infra.provider;

import com.fiap.fast_food_tc.infrastructure.persistence.entity.Product;
import com.fiap.fast_food_tc.infrastructure.persistence.repository.ProductRepository;
import com.fiap.fast_food_tc.infrastructure.persistence.dataprovider.ProductDataProvider;
import fixture.ProductFixture;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductDataProviderTest {

    @Mock
    private ProductRepository repository;

    @InjectMocks
    private ProductDataProvider provider;

    @Test
    void createSuccess() {
        Product model = ProductFixture.createProduct();
        when(repository.save(model)).thenReturn(model);

        var result = provider.create(model);

        assertEquals(model, result);
    }

    @Test
    void findAllSuccess() {
        List<Product> list = List.of(ProductFixture.createProduct());
        when(repository.findAll()).thenReturn(list);

        var result = provider.findAll();

        assertEquals(list, result);
    }

    @Test
    void updateSuccess() {
        Product model = ProductFixture.createProduct();
        when(repository.save(any())).thenReturn(model);

        var result = provider.update(model);

        assertEquals(model, result);
    }

    @Test
    void deleteSuccess() {
        provider.delete(1);

        verify(repository).deleteById(1);
    }

    @Test
    void findByIdSuccess() {
        Product model = ProductFixture.createProduct();
        when(repository.findById(1)).thenReturn(Optional.of(model));

        var result = provider.findById(1);

        assertEquals(model, result);
    }

    @Test
    void findByCategoryIdSuccess() {
        List<Product> list = List.of(ProductFixture.createProduct());
        when(repository.findByCategoryCategoryId(1)).thenReturn(list);

        var result = provider.findByCategoryId(1);

        assertEquals(list, result);
    }
}
