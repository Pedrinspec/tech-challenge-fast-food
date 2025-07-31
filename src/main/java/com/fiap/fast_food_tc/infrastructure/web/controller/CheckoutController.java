package com.fiap.fast_food_tc.infrastructure.web.controller;

import com.fiap.fast_food_tc.application.dto.checkout.in.CheckoutOrderRequest;
import com.fiap.fast_food_tc.application.dto.checkout.out.CheckoutResponseDto;
import com.fiap.fast_food_tc.application.dto.checkout.in.CheckoutWebhookRequest;
import com.fiap.fast_food_tc.application.service.CheckoutService;
import com.fiap.fast_food_tc.domain.entity.Orders;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Checkout", description = "Endpoints de checkout")
@RestController
@RequestMapping("/checkout")
public class CheckoutController {

    private final CheckoutService checkoutService;

    @Autowired
    public CheckoutController(CheckoutService checkoutService) {
        this.checkoutService = checkoutService;
    }

    @PostMapping
    public ResponseEntity<CheckoutResponseDto> checkout(@RequestBody CheckoutOrderRequest request) {
        return ResponseEntity.ok(checkoutService.checkoutAndCreateOrder(request));
    }

    @PostMapping("/webhook/mercadoPago")
    public ResponseEntity<Orders> handleWebhook(@Valid @RequestBody CheckoutWebhookRequest payload) {

        if ("payment".equals(payload.getType()) && payload.getData() != null) {
            String paymentId = payload.getData().getId();

            if (paymentId != null && !paymentId.isEmpty()) {
                return ResponseEntity.ok(checkoutService.handleWebhook(paymentId));
            }
        }
        throw new IllegalArgumentException("Invalid webhook payload or missing payment ID");
    }

}
