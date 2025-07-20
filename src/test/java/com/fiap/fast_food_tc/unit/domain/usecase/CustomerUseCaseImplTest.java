package com.fiap.fast_food_tc.unit.domain.usecase;

import com.fiap.fast_food_tc.domain.entity.Customer;
import com.fiap.fast_food_tc.infrastructure.web.rest.mapper.CustomerMapper;
import com.fiap.fast_food_tc.application.gateway.CustomerGateway;
import com.fiap.fast_food_tc.application.usecase.impl.CustomerUseCaseImpl;
import fixture.CustomerFixture;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class CustomerUseCaseImplTest {

    @Mock
    private CustomerGateway customerGateway;
    @Mock
    private CustomerMapper customerMapper;
    @InjectMocks
    private CustomerUseCaseImpl useCase;

    @Test
    void createSuccess() {
        Customer input = CustomerFixture.createECustomer();
        com.fiap.fast_food_tc.infrastructure.persistence.entity.Customer model = CustomerFixture.createCustomerModel();
        Customer expected = CustomerFixture.createECustomer();

        Mockito.when(customerMapper.toDomain(input)).thenReturn(model);
        Mockito.when(customerGateway.create(model)).thenReturn(model);
        Mockito.when(customerMapper.toEntity(model)).thenReturn(expected);

        var result = useCase.create(input);

        assertEquals(expected, result);
    }

    @Test
    void getByDocumentNumberSuccess() {
        com.fiap.fast_food_tc.infrastructure.persistence.entity.Customer model = CustomerFixture.createCustomerModel();
        Customer expected = CustomerFixture.createECustomer();

        Mockito.when(customerGateway.findByDocumentNumber("111111")).thenReturn(model);
        Mockito.when(customerMapper.toEntity(model)).thenReturn(expected);

        var result = useCase.getByDocumentNumber("111111");

        assertEquals(expected, result);
    }

    @Test
    void getByIdSuccess() {
        com.fiap.fast_food_tc.infrastructure.persistence.entity.Customer model = CustomerFixture.createCustomerModel();
        Customer expected = CustomerFixture.createECustomer();

        Mockito.when(customerGateway.findById(1)).thenReturn(model);
        Mockito.when(customerMapper.toEntity(model)).thenReturn(expected);

        var result = useCase.getById(1);

        assertEquals(expected, result);
    }

    @Test
    void getAllSuccess() {
        java.util.List<com.fiap.fast_food_tc.infrastructure.persistence.entity.Customer> models = java.util.List.of(CustomerFixture.createCustomerModel());
        java.util.List<Customer> entities = java.util.List.of(CustomerFixture.createECustomer());

        Mockito.when(customerGateway.findAll()).thenReturn(models);
        Mockito.when(customerMapper.toEntityList(models)).thenReturn(entities);

        var result = useCase.getAll();

        assertEquals(entities, result);
    }

    @Test
    void updateSuccess() {
        Customer entity = CustomerFixture.createECustomer();
        com.fiap.fast_food_tc.infrastructure.persistence.entity.Customer model = CustomerFixture.createCustomerModel();

        Mockito.when(customerMapper.toDomain(entity)).thenReturn(model);
        Mockito.when(customerGateway.update(model)).thenReturn(model);
        Mockito.when(customerMapper.toEntity(model)).thenReturn(entity);

        var result = useCase.update(entity);

        assertEquals(entity, result);
    }

    @Test
    void deleteSuccess() {
        useCase.delete(1);

        Mockito.verify(customerGateway).delete(1);
    }
}
