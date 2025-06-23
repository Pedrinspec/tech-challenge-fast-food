package com.fiap.fast_food_tc.app.controller;

import com.fiap.fast_food_tc.app.dto.checkout.CheckoutOrderRequest;
import com.fiap.fast_food_tc.app.dto.checkout.CheckoutResponseDto;
import com.fiap.fast_food_tc.app.service.CheckoutService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

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
    public ResponseEntity<Void> handleWebhook(@RequestBody Map<String, Object> payload) {
        Map<String, Object> data = (Map<String, Object>) payload.get("data");
        String type = (String) payload.get("type");

        if ("payment".equals(type) && data != null) {
            String paymentId = data.get("id").toString();
            checkoutService.handleWebhook(paymentId);
        }
        return ResponseEntity.noContent().build();
    }

}
