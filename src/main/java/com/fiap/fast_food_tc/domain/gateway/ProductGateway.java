package com.fiap.fast_food_tc.domain.gateway;

import com.fiap.fast_food_tc.adapter.db.model.Product;
import com.fiap.fast_food_tc.adapter.dto.ProductResponseDto;

import java.util.List;
import java.util.Optional;

public interface ProductGateway {
    Product create(Product domain);

    Product update(Product domain);

    void delete(Product domain);

    Optional<Product> findById(Long id);

    List<Product> getByCategoryId(long categoryId);
}
