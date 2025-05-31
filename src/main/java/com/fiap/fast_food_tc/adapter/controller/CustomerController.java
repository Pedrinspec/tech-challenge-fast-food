package com.fiap.fast_food_tc.adapter.controller;

import com.fiap.fast_food_tc.adapter.dto.customer.CustomerRequestDto;
import com.fiap.fast_food_tc.adapter.dto.customer.CustomerResponseDto;
import com.fiap.fast_food_tc.app.service.CustomerService;
import com.fiap.fast_food_tc.app.service.impl.CustomerServiceImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Customer", description = "Endpoints de usuários")
@RestController
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerServiceImpl customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<CustomerResponseDto> createCustomer(@RequestBody @Valid CustomerRequestDto customer) {
        var customerCreated = customerService.create(customer);
        return ResponseEntity.status(HttpStatus.CREATED).body(customerCreated);
    }

    @GetMapping("/{documentNumber}")
    public ResponseEntity<CustomerResponseDto> getCustomerById(@PathVariable("documentNumber") String documentNumber) {
        var customer = customerService.getByDoc(documentNumber);
        return ResponseEntity.ok(customer);
    }

}
