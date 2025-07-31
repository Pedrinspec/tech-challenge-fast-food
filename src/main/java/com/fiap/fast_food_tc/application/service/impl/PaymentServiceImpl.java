package com.fiap.fast_food_tc.application.service.impl;

import com.fiap.fast_food_tc.application.dto.payment.in.PaymentRequestDto;
import com.fiap.fast_food_tc.application.dto.payment.out.PaymentResponseDto;
import com.fiap.fast_food_tc.application.dto.payment.out.PaymentStatusResponseDto;
import com.fiap.fast_food_tc.application.service.PaymentService;
import com.fiap.fast_food_tc.infrastructure.web.mapper.PaymentMapper;
import com.fiap.fast_food_tc.application.usecase.OrdersUseCase;
import com.fiap.fast_food_tc.application.usecase.PaymentUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentUseCase useCase;
    private final PaymentMapper mapper;
    private final OrdersUseCase ordersUseCase;

    @Autowired
    public PaymentServiceImpl(PaymentUseCase useCase, PaymentMapper mapper, OrdersUseCase ordersUseCase) {
        this.useCase = useCase;
        this.mapper = mapper;
        this.ordersUseCase = ordersUseCase;
    }

    @Override
    public PaymentResponseDto create(PaymentRequestDto dto) {
        var entity = mapper.toEntityCreate(dto);
        return mapper.toResponse(useCase.create(entity));
    }

    @Override
    public List<PaymentResponseDto> findAll() {
        return mapper.toResponseList(useCase.findAll());
    }

    @Override
    public PaymentResponseDto findById(Integer id) {
        return mapper.toResponse(useCase.findById(id));
    }

    @Override
    public PaymentStatusResponseDto getStatusByOrderId(Integer orderId) {
        var payment = useCase.findByOrderId(orderId);
        var order = ordersUseCase.getById(orderId);
        return PaymentStatusResponseDto.builder()
                .orderId(orderId)
                .orderCode(order.getOrderCode())
                .paymentStatus(payment.getPaymentStatus())
                .build();
    }
}
