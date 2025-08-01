package com.fiap.fast_food_tc.unit.infra.provider;

import com.fiap.fast_food_tc.infrastructure.persistence.entity.CustomerPersistenceEntity;
import com.fiap.fast_food_tc.infrastructure.persistence.repository.CustomerRepository;
import com.fiap.fast_food_tc.infrastructure.persistence.dataprovider.CustomerDataProvider;
import fixture.CustomerFixture;
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
class CustomerDataProviderTest {

    @Mock
    private CustomerRepository repository;

    @InjectMocks
    private CustomerDataProvider provider;

    @Test
    void createSuccess() {
        CustomerPersistenceEntity model = CustomerFixture.createCustomerModel();
        when(repository.save(model)).thenReturn(model);

        var result = provider.create(model);

        assertEquals(model, result);
    }

    @Test
    void findByDocumentNumberSuccess() {
        CustomerPersistenceEntity model = CustomerFixture.createCustomerModel();
        when(repository.findByDocumentNumber("111111")).thenReturn(Optional.of(model));

        var result = provider.findByDocumentNumber("111111");

        assertEquals(model, result);
    }

    @Test
    void findByIdSuccess() {
        CustomerPersistenceEntity model = CustomerFixture.createCustomerModel();
        when(repository.findById(1)).thenReturn(Optional.of(model));

        var result = provider.findById(1);

        assertEquals(model, result);
    }

    @Test
    void updateSuccess() {
        CustomerPersistenceEntity model = CustomerFixture.createCustomerModel();
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
    void findAllSuccess() {
        List<CustomerPersistenceEntity> list = List.of(CustomerFixture.createCustomerModel());
        when(repository.findAll()).thenReturn(list);

        var result = provider.findAll();

        assertEquals(list, result);
    }
}
