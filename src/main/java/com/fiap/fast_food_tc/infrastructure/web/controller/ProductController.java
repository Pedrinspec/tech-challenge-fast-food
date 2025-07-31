package com.fiap.fast_food_tc.infrastructure.web.controller;

import com.fiap.fast_food_tc.application.dto.product.in.ProductRequest;
import com.fiap.fast_food_tc.application.dto.product.out.ProductResponse;
import com.fiap.fast_food_tc.application.service.ProductService;
import com.fiap.fast_food_tc.application.service.impl.ProductServiceImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Product", description = "Endpoints de produtos")
@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductServiceImpl productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(productService.findById(id));
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<ProductResponse>> getByCategoryId(@PathVariable Integer categoryId) {
        return ResponseEntity.ok(productService.findByCategoryId(categoryId));
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAll() {
        return ResponseEntity.ok(productService.findAll());
    }

    @PostMapping
    public ResponseEntity<ProductResponse> create(@Valid @RequestBody ProductRequest dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> update(@PathVariable Integer id, @Valid @RequestBody ProductRequest dto) {
        return ResponseEntity.ok(productService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }

}