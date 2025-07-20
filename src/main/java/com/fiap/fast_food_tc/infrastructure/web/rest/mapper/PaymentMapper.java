package com.fiap.fast_food_tc.infrastructure.web.rest.mapper;

import com.fiap.fast_food_tc.application.dto.payment.PaymentRequestDto;
import com.fiap.fast_food_tc.application.dto.payment.PaymentResponseDto;
import com.fiap.fast_food_tc.domain.entity.Payment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PaymentMapper {

    @Mapping(target = "paymentId", ignore = true)
    @Mapping(target = "orders.orderId", source = "orderId")
    com.fiap.fast_food_tc.infrastructure.persistence.entity.Payment toModel(Payment entity);

    @Mapping(target = "orderId", source = "orders.orderId")
    Payment toEntity(com.fiap.fast_food_tc.infrastructure.persistence.entity.Payment model);

    @Mapping(target = "orderId", source = "orderId")
    @Mapping(target = "paymentId", ignore = true)
    Payment toEntityCreate(PaymentRequestDto dto);

    PaymentResponseDto toResponse(Payment entity);

    List<PaymentResponseDto> toResponseList(List<Payment> list);

    List<Payment> toEntityList(List<com.fiap.fast_food_tc.infrastructure.persistence.entity.Payment> list);
}