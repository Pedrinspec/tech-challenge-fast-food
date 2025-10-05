package com.fiap.fast_food_tc.infrastructure.web.controller;

import com.fiap.fast_food_tc.application.dto.payment.in.PaymentRequestDto;
import com.fiap.fast_food_tc.application.dto.payment.out.PaymentResponseDto;
import com.fiap.fast_food_tc.application.dto.payment.out.PaymentStatusResponseDto;
import com.fiap.fast_food_tc.application.service.PaymentService;
import com.fiap.fast_food_tc.application.service.impl.PaymentServiceImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<PaymentResponseDto> create(@RequestBody @Valid PaymentRequestDto dto) {
        var created = service.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<PaymentResponseDto>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<PaymentResponseDto> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping(path = "/status/{orderId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<PaymentStatusResponseDto> getStatusByOrder(@PathVariable Integer orderId) {
        return ResponseEntity.ok(service.getStatusByOrderId(orderId));
    }
}
