package com.fiap.fast_food_tc.application.service;

import com.fiap.fast_food_tc.application.dto.payment.in.PaymentRequestDto;
import com.fiap.fast_food_tc.application.dto.payment.out.PaymentResponseDto;
import com.fiap.fast_food_tc.application.dto.payment.out.PaymentStatusResponseDto;

import java.util.List;

public interface PaymentService {

    PaymentResponseDto create(PaymentRequestDto dto);

    List<PaymentResponseDto> findAll();

    PaymentResponseDto findById(Integer id);

    PaymentStatusResponseDto getStatusByOrderId(Integer orderId);

}
