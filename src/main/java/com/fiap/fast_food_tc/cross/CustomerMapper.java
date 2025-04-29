package com.fiap.fast_food_tc.cross;

import com.fiap.fast_food_tc.adapter.db.model.Customer;
import com.fiap.fast_food_tc.adapter.dto.customerDto.CustomerRequestDto;
import com.fiap.fast_food_tc.adapter.dto.customerDto.CustomerResponseDto;
import com.fiap.fast_food_tc.domain.entity.ECustomer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    ECustomer toEntity(Customer eCustomer);

    Customer toDomain(ECustomer customer);

    @Mapping(target = "customerId", ignore = true)
    @Mapping(target = "orders", ignore = true)
    ECustomer messageToEntity(CustomerRequestDto customer);

    CustomerResponseDto entityToMessage(ECustomer customer);

}
