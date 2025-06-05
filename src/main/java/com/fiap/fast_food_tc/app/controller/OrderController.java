package com.fiap.fast_food_tc.app.controller;

import com.fiap.fast_food_tc.app.dto.orders.OrdersRequestDto;
import com.fiap.fast_food_tc.app.dto.orders.OrdersResponseDto;
import com.fiap.fast_food_tc.app.service.OrdersService;
import com.fiap.fast_food_tc.app.service.impl.OrdersServiceImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Order", description = "Endpoints de pedidos")
@RestController
@RequestMapping("orders")
public class OrderController {

    private final OrdersService ordersService;

    @Autowired
    public OrderController(OrdersServiceImpl ordersService) {
        this.ordersService = ordersService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<OrdersResponseDto>> getAllOrders() {
        var response = ordersService.getAllOrders();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrdersResponseDto> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(ordersService.getOrderById(id));
    }

    @PostMapping
    public ResponseEntity<OrdersResponseDto> createOrder(@RequestBody @Valid OrdersRequestDto order) {
        var ordersCreated = ordersService.create(order);
        return ResponseEntity.status(HttpStatus.CREATED).body(ordersCreated);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrdersResponseDto> update(@PathVariable Integer id, @RequestBody @Valid OrdersRequestDto dto) {
        var updated = ordersService.update(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        ordersService.delete(id);
        return ResponseEntity.noContent().build();
    }


}
