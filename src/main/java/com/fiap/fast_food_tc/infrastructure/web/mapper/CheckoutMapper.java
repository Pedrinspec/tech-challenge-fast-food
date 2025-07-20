package com.fiap.fast_food_tc.infrastructure.web.mapper;

import com.fiap.fast_food_tc.application.dto.checkout.CheckoutOrderRequest;
import com.fiap.fast_food_tc.application.dto.checkout.CheckoutResponseDto;
import com.fiap.fast_food_tc.domain.entity.Checkout;
import com.fiap.fast_food_tc.domain.entity.CheckoutOrder;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CheckoutMapper {

    CheckoutResponseDto toResponse(Checkout checkout);

    Checkout toEntity(CheckoutResponseDto checkoutResponseDto);

    CheckoutOrder toEntityRequest(CheckoutOrderRequest checkoutOrderRequest);

}
