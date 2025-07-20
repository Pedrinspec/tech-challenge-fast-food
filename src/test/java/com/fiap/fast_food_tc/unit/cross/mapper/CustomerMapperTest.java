package com.fiap.fast_food_tc.unit.cross.mapper;

import com.fiap.fast_food_tc.infrastructure.persistence.entity.Customer;
import com.fiap.fast_food_tc.application.dto.customer.CustomerRequestDto;
import com.fiap.fast_food_tc.application.dto.customer.CustomerResponseDto;
import com.fiap.fast_food_tc.infrastructure.web.rest.mapper.CustomerMapper;
import com.fiap.fast_food_tc.domain.entity.ECustomer;
import fixture.CustomerFixture;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CustomerMapperTest {

    private final CustomerMapper mapper = Mappers.getMapper(CustomerMapper.class);

    @Test
    void toEntitySuccess() {
        Customer customer = CustomerFixture.createCustomerModel();

        ECustomer result = mapper.toEntity(customer);

        assertEquals(customer.getCustomerId(), result.getCustomerId());
        assertEquals(customer.getDocumentNumber(), result.getDocumentNumber());
        assertEquals(customer.getFirstName(), result.getFirstName());
        assertEquals(customer.getLastName(), result.getLastName());
        assertEquals(customer.getEmail(), result.getEmail());
    }

    @Test
    void toDomainSuccess() {
        ECustomer entity = CustomerFixture.createECustomer();

        Customer result = mapper.toDomain(entity);

        assertEquals(entity.getCustomerId(), result.getCustomerId());
        assertEquals(entity.getDocumentNumber(), result.getDocumentNumber());
        assertEquals(entity.getFirstName(), result.getFirstName());
        assertEquals(entity.getLastName(), result.getLastName());
        assertEquals(entity.getEmail(), result.getEmail());
    }

    @Test
    void messageToEntitySuccess() {
        CustomerRequestDto request = CustomerFixture.createCustomerRequestDto();

        ECustomer entity = mapper.messageToEntity(request);

        assertNull(entity.getCustomerId());
        assertEquals(request.getDocumentNumber(), entity.getDocumentNumber());
        assertEquals(request.getFirstName(), entity.getFirstName());
        assertEquals(request.getLastName(), entity.getLastName());
        assertEquals(request.getEmail(), entity.getEmail());
    }

    @Test
    void entityToMessageSuccess() {
        ECustomer entity = CustomerFixture.createECustomer();

        CustomerResponseDto dto = mapper.entityToMessage(entity);

        assertEquals(entity.getCustomerId(), dto.getCustomerId());
        assertEquals(entity.getDocumentNumber(), dto.getDocumentNumber());
        assertEquals(entity.getFirstName(), dto.getFirstName());
        assertEquals(entity.getLastName(), dto.getLastName());
        assertEquals(entity.getEmail(), dto.getEmail());
    }

    @Test
    void entityToMessageListSuccess() {
        var entities = List.of(CustomerFixture.createECustomer());

        var result = mapper.entityToMessageList(entities);

        assertEquals(1, result.size());
        assertEquals(entities.getFirst().getCustomerId(), result.getFirst().getCustomerId());
    }

    @Test
    void toEntityListSuccess() {
        var models = List.of(CustomerFixture.createCustomerModel());

        var result = mapper.toEntityList(models);

        assertEquals(1, result.size());
        assertEquals(models.getFirst().getCustomerId(), result.getFirst().getCustomerId());
    }

}
