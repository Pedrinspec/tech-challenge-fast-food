package com.fiap.fast_food_tc.infrastructure.web.controller;

import com.fiap.fast_food_tc.application.dto.orders.in.OrdersRequestDto;
import com.fiap.fast_food_tc.application.dto.orders.out.OrdersResponseDto;
import com.fiap.fast_food_tc.application.dto.orders.out.UpdateStatusOrderRequest;
import com.fiap.fast_food_tc.application.service.OrdersService;
import com.fiap.fast_food_tc.application.service.impl.OrdersServiceImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

    @GetMapping(path = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<OrdersResponseDto>> getAllOrders() {
        var response = ordersService.getAllOrders();
        return ResponseEntity.ok(response);
    }

    @GetMapping(path = "/allOrderUnfinished", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<OrdersResponseDto>> getAllOrderUnfinished() {
        var response = ordersService.getAllOrderUnfinished();
        return ResponseEntity.ok(response);
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<OrdersResponseDto> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(ordersService.getOrderById(id));
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<OrdersResponseDto> createOrder(@RequestBody @Valid OrdersRequestDto order) {
        var ordersCreated = ordersService.create(order);
        return ResponseEntity.status(HttpStatus.CREATED).body(ordersCreated);
    }

    @PutMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<OrdersResponseDto> update(@PathVariable Integer id, @RequestBody @Valid OrdersRequestDto dto) {
        var updated = ordersService.update(id, dto);
        return ResponseEntity.ok(updated);
    }

    @PatchMapping(path = "/{id}/status", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<OrdersResponseDto> updateStatus(@PathVariable Integer id, @RequestBody UpdateStatusOrderRequest status) {
        var updated = ordersService.updateStatus(id, status.getNewStatusOrder());
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        ordersService.delete(id);
        return ResponseEntity.noContent().build();
    }


}
