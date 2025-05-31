package com.fiap.fast_food_tc.adapter.controller;

import com.fiap.fast_food_tc.app.service.CheckoutService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Checkout", description = "Endpoints de checkout")
@RestController
@RequestMapping("/checkout")
public class CheckoutController {

    private final CheckoutService checkoutService;

    @Autowired
    public CheckoutController( CheckoutService checkoutService) {
        this.checkoutService = checkoutService;
    }

    @PostMapping("/{orderId}")
    public ResponseEntity<String> checkout(@PathVariable Integer orderId) {
        return ResponseEntity.ok(checkoutService.paymentPreferenceProcess(orderId));
    }

}
