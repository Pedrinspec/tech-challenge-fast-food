package com.fiap.fast_food_tc.application.usecase;

import com.fiap.fast_food_tc.domain.entity.Product;

import java.util.List;

public interface ProductUseCase {
    Product create(Product product);

    List<Product> findAll();

    Product updateCustomer(Integer id, Product dto);

    void deleteProduct(Integer id);

    List<Product> findByCategoryId(Integer categoryId);

    Product findById(Integer id);

    void subtractQuantity(Integer id, Integer quantityToRemove);
}
