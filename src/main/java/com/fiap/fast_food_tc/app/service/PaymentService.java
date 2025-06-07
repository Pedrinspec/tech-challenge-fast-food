package com.fiap.fast_food_tc.app.service;

import com.fiap.fast_food_tc.app.dto.payment.PaymentRequestDto;
import com.fiap.fast_food_tc.app.dto.payment.PaymentResponseDto;

import java.util.List;

public interface PaymentService {

    PaymentResponseDto create(PaymentRequestDto dto);

    List<PaymentResponseDto> findAll();

    PaymentResponseDto findById(Integer id);

}
