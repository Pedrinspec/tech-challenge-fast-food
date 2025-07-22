package com.fiap.fast_food_tc.infrastructure.web.controller;

import com.fiap.fast_food_tc.application.dto.orderproduct.OrderProductRequestDto;
import com.fiap.fast_food_tc.application.dto.orderproduct.OrderProductResponseDto;
import com.fiap.fast_food_tc.application.service.OrderProductService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "OrderProduct", description = "Endpoints de relação de pedidos e produtos")
@RestController
@RequestMapping("order-products")
public class OrderProductController {

    private final OrderProductService service;

    @Autowired
    public OrderProductController(OrderProductService service) {
        this.service = service;
    }

    @GetMapping("/all")
    public ResponseEntity<List<OrderProductResponseDto>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{orderId}/{productId}")
    public ResponseEntity<OrderProductResponseDto> getById(@PathVariable Integer orderId, @PathVariable Integer productId) {
        return ResponseEntity.ok(service.getById(orderId, productId));
    }

    @PostMapping
    public ResponseEntity<OrderProductResponseDto> create(@RequestBody @Valid OrderProductRequestDto dto) {
        var created = service.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{orderId}/{productId}")
    public ResponseEntity<OrderProductResponseDto> update(@PathVariable Integer orderId, @PathVariable Integer productId, @RequestBody @Valid OrderProductRequestDto dto) {
        return ResponseEntity.ok(service.update(orderId, productId, dto));
    }

    @DeleteMapping("/{orderId}/{productId}")
    public ResponseEntity<Void> delete(@PathVariable Integer orderId, @PathVariable Integer productId) {
        service.delete(orderId, productId);
        return ResponseEntity.noContent().build();
    }
}
