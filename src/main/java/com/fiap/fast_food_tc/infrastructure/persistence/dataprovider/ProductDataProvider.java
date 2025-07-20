package com.fiap.fast_food_tc.infrastructure.persistence.dataprovider;

import com.fiap.fast_food_tc.infrastructure.persistence.entity.Product;
import com.fiap.fast_food_tc.infrastructure.persistence.repository.ProductRepository;
import com.fiap.fast_food_tc.application.gateway.ProductGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
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

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product update(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void delete(Integer id) {
        productRepository.deleteById(id);
    }

    @Override
    public Product findById(Integer id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
    }

    @Override
    public List<Product> findByCategoryId(Integer categoryId) {
        return productRepository.findByCategoryCategoryId(categoryId);
    }

}
