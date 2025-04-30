package com.fiap.fast_food_tc.domain.gateway;

import com.fiap.fast_food_tc.adapter.db.model.Product;

import java.util.Optional;

public interface ProductGateway {
    Product create(Product domain);

    Product update(Product domain);

    void delete(Product domain);

    Optional<Product> findById(Long id);
}
