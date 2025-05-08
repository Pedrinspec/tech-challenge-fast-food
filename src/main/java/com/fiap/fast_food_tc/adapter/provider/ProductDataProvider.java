package com.fiap.fast_food_tc.adapter.provider;

import com.fiap.fast_food_tc.adapter.db.model.Product;
import com.fiap.fast_food_tc.adapter.db.repository.ProductRepository;
import com.fiap.fast_food_tc.domain.gateway.ProductGateway;
import org.springframework.beans.factory.annotation.Autowired;

public class ProductDataProvider implements ProductGateway {

    private final ProductRepository productRepository;

    @Autowired
    public ProductDataProvider(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product create(Product product) {
        return productRepository.save(product);
    }

}
