package com.fiap.fast_food_tc.cross.mapper;

import com.fiap.fast_food_tc.app.dto.payment.PaymentRequestDto;
import com.fiap.fast_food_tc.app.dto.payment.PaymentResponseDto;
import com.fiap.fast_food_tc.domain.entity.EPayment;
import com.fiap.fast_food_tc.infra.db.model.Payment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PaymentMapper {

    @Mapping(target = "paymentId", ignore = true)
    @Mapping(target = "orders.orderId", source = "orderId")
    Payment toModel(EPayment entity);

    @Mapping(target = "orderId", source = "orders.orderId")
    EPayment toEntity(Payment model);

    @Mapping(target = "orderId", source = "orderId")
    EPayment toEntityCreate(PaymentRequestDto dto);

    PaymentResponseDto toResponse(EPayment entity);

    List<PaymentResponseDto> toResponseList(List<EPayment> list);

    List<EPayment> toEntityList(List<Payment> list);
}