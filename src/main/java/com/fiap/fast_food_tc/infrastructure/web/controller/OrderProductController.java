package com.fiap.fast_food_tc.infrastructure.web.controller;

import com.fiap.fast_food_tc.application.dto.orderproduct.in.OrderProductRequestDto;
import com.fiap.fast_food_tc.application.dto.orderproduct.out.OrderProductResponseDto;
import com.fiap.fast_food_tc.application.service.OrderProductService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

    @GetMapping(path = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<OrderProductResponseDto>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping(path = "/{orderId}/{productId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<OrderProductResponseDto> getById(@PathVariable Integer orderId, @PathVariable Integer productId) {
        return ResponseEntity.ok(service.getById(orderId, productId));
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<OrderProductResponseDto> create(@RequestBody @Valid OrderProductRequestDto dto) {
        var created = service.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping(path = "/{orderId}/{productId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<OrderProductResponseDto> update(@PathVariable Integer orderId, @PathVariable Integer productId, @RequestBody @Valid OrderProductRequestDto dto) {
        return ResponseEntity.ok(service.update(orderId, productId, dto));
    }

    @DeleteMapping("/{orderId}/{productId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> delete(@PathVariable Integer orderId, @PathVariable Integer productId) {
        service.delete(orderId, productId);
        return ResponseEntity.noContent().build();
    }
}
