package com.fiap.fast_food_tc.app.controller;

import com.fiap.fast_food_tc.app.dto.payment.PaymentRequestDto;
import com.fiap.fast_food_tc.app.dto.payment.PaymentResponseDto;
import com.fiap.fast_food_tc.app.service.PaymentService;
import com.fiap.fast_food_tc.app.service.impl.PaymentServiceImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Payment", description = "Endpoints de pagamentos")
@RestController
@RequestMapping("/payment")
public class PaymentController {

    private final PaymentService service;

    @Autowired
    public PaymentController(PaymentServiceImpl service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<PaymentResponseDto> create(@RequestBody @Valid PaymentRequestDto dto) {
        var created = service.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping
    public ResponseEntity<List<PaymentResponseDto>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentResponseDto> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(service.findById(id));
    }
}
