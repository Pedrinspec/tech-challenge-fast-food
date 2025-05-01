package com.fiap.fast_food_tc.adapter.controller;

import com.fiap.fast_food_tc.adapter.dto.ProductRequestDto;
import com.fiap.fast_food_tc.adapter.dto.ProductResponseDto;
import com.fiap.fast_food_tc.app.service.ProductService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@Tag(name = "Product", description = "Products Endpoints")
@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<ProductResponseDto> createProduct(@RequestBody ProductRequestDto product) {
        var productCreated = productService.create(product);
        return ResponseEntity.created(URI.create("/product/" + productCreated.getProductId())).body(productCreated);
    }

    @PutMapping("{id}")
    public ResponseEntity<ProductResponseDto> createProduct(@PathVariable long id, @RequestBody ProductRequestDto product) {
         return ResponseEntity.ok(productService.update(id,product));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUser(@PathVariable long id) {
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping()
    public List<ProductResponseDto> getByCategory(@RequestParam long categoryId) {
       List<ProductResponseDto> responseDtoList = productService.getByCategoryId(categoryId) ;

        return responseDtoList;
    }


}
