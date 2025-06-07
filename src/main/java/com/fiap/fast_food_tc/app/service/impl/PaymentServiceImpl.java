package com.fiap.fast_food_tc.app.service.impl;

import com.fiap.fast_food_tc.app.dto.payment.PaymentRequestDto;
import com.fiap.fast_food_tc.app.dto.payment.PaymentResponseDto;
import com.fiap.fast_food_tc.app.service.PaymentService;
import com.fiap.fast_food_tc.cross.mapper.PaymentMapper;
import com.fiap.fast_food_tc.domain.usecase.PaymentUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentUseCase useCase;
    private final PaymentMapper mapper;

    @Autowired
    public PaymentServiceImpl(PaymentUseCase useCase, PaymentMapper mapper) {
        this.useCase = useCase;
        this.mapper = mapper;
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
}
