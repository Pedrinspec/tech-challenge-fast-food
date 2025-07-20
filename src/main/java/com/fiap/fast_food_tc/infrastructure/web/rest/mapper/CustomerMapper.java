package com.fiap.fast_food_tc.infrastructure.web.rest.mapper;

import com.fiap.fast_food_tc.application.dto.customer.CustomerRequestDto;
import com.fiap.fast_food_tc.application.dto.customer.CustomerResponseDto;
import com.fiap.fast_food_tc.domain.entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    Customer toEntity(com.fiap.fast_food_tc.infrastructure.persistence.entity.Customer customer);

    @Mapping(target = "orders", ignore = true)
    com.fiap.fast_food_tc.infrastructure.persistence.entity.Customer toDomain(Customer customer);

    @Mapping(target = "customerId", ignore = true)
    Customer messageToEntity(CustomerRequestDto customer);

    CustomerResponseDto entityToMessage(Customer customer);

    List<CustomerResponseDto> entityToMessageList(List<Customer> customers);

    List<Customer> toEntityList(List<com.fiap.fast_food_tc.infrastructure.persistence.entity.Customer> customers);

}
