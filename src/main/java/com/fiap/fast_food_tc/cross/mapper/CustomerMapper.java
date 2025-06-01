package com.fiap.fast_food_tc.cross.mapper;

import com.fiap.fast_food_tc.adapter.db.model.Customer;
import com.fiap.fast_food_tc.adapter.dto.customer.CustomerRequestDto;
import com.fiap.fast_food_tc.adapter.dto.customer.CustomerResponseDto;
import com.fiap.fast_food_tc.domain.entity.ECustomer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    ECustomer toEntity(Customer eCustomer);

    @Mapping(target = "orders", ignore = true)
    Customer toDomain(ECustomer customer);

    @Mapping(target = "customerId", ignore = true)
    ECustomer messageToEntity(CustomerRequestDto customer);

    CustomerResponseDto entityToMessage(ECustomer customer);

    List<CustomerResponseDto> entityToMessageList(List<ECustomer> customers);

    List<ECustomer> toEntityList(List<Customer> customers);

}
