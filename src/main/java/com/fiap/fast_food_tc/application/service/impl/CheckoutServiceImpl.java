package com.fiap.fast_food_tc.application.service.impl;

import com.fiap.fast_food_tc.application.dto.checkout.CheckoutOrderRequest;
import com.fiap.fast_food_tc.application.dto.checkout.CheckoutResponseDto;
import com.fiap.fast_food_tc.application.service.CheckoutService;
import com.fiap.fast_food_tc.infrastructure.web.mapper.CheckoutMapper;
import com.fiap.fast_food_tc.application.usecase.CheckoutUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CheckoutServiceImpl implements CheckoutService {

    private final CheckoutUseCase checkoutUseCase;
    private final CheckoutMapper checkoutMapper;
    @Autowired
    public CheckoutServiceImpl(CheckoutUseCase checkoutUseCase, CheckoutMapper checkoutMapper) {
        this.checkoutUseCase = checkoutUseCase;
        this.checkoutMapper = checkoutMapper;
    }


    @Override
    public CheckoutResponseDto checkoutAndCreateOrder(CheckoutOrderRequest request) {
        return checkoutMapper.toResponse(checkoutUseCase.checkoutAndCreateOrder(checkoutMapper.toEntityRequest(request)));
    }

    @Override
    public void handleWebhook(String paymentId) {
        checkoutUseCase.handleWebhook(paymentId);
    }
}
