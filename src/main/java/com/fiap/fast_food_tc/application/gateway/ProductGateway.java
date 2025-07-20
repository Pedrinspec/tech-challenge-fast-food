package com.fiap.fast_food_tc.application.gateway;

import com.fiap.fast_food_tc.infrastructure.persistence.entity.Product;

import java.util.List;

public interface ProductGateway {
    Product create(Product eProduct);

    List<Product> findAll();

    Product update(Product product);

    void delete(Integer id);

    Product findById(Integer id);

    List<Product> findByCategoryId(Integer categoryId);
}
