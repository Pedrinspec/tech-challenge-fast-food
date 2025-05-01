package com.fiap.fast_food_tc.adapter.provider;

import com.fiap.fast_food_tc.adapter.db.model.Product;
import com.fiap.fast_food_tc.adapter.db.repository.ProductRepository;
import com.fiap.fast_food_tc.adapter.dto.ProductResponseDto;
import com.fiap.fast_food_tc.domain.gateway.ProductGateway;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ProductDataProvider implements ProductGateway {

    private final ProductRepository repository;

    public ProductDataProvider(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public Product create(Product product) {
        return repository.save(product);
    }


    @Override
    public Product update(Product product) {
        return repository.save(product);
    }

    @Override
    public void delete(Product product) {
        repository.delete(product);
    }

    @Override
    public Optional<Product> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Product> getByCategoryId(long categoryId) {
        return repository.findByCategory_CategoryId(categoryId);
    }

}
