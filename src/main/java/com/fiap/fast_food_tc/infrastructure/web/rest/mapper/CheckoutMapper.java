package com.fiap.fast_food_tc.infrastructure.web.rest.mapper;

import com.fiap.fast_food_tc.application.dto.checkout.CheckoutOrderRequest;
import com.fiap.fast_food_tc.application.dto.checkout.CheckoutResponseDto;
import com.fiap.fast_food_tc.domain.entity.ECheckout;
import com.fiap.fast_food_tc.domain.entity.ECheckoutOrder;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CheckoutMapper {

    CheckoutResponseDto toResponse(ECheckout eCheckout);

    ECheckout toEntity(CheckoutResponseDto checkoutResponseDto);

    ECheckoutOrder toEntityRequest(CheckoutOrderRequest checkoutOrderRequest);

}
