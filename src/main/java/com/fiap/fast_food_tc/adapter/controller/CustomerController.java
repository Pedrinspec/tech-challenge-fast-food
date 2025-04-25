package com.fiap.fast_food_tc.adapter.controller;

import com.fiap.fast_food_tc.adapter.dto.CustomerRequestDto;
import com.fiap.fast_food_tc.adapter.dto.CustomerResponseDto;
import com.fiap.fast_food_tc.app.service.CustomerService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@Tag(name = "Customer", description = "Endpoints de usuários")
@RestController
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<CustomerResponseDto> createCustomer(@RequestBody CustomerRequestDto user) {
        var userCreated = customerService.create(user);
        return ResponseEntity.created(URI.create("/user/" + userCreated.getCustomerId())).body(userCreated);
    }

    @GetMapping("/{documentNumber}")
    public ResponseEntity<CustomerResponseDto> getCustomerById(@PathVariable("documentNumber") String documentNumber) {
        var customer = customerService.getByDoc(documentNumber);
        return ResponseEntity.ok(customer);
    }


}
