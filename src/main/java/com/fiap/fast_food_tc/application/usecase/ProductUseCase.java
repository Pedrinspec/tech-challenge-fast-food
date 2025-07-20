package com.fiap.fast_food_tc.application.usecase;

import com.fiap.fast_food_tc.domain.entity.EProduct;

import java.util.List;

public interface ProductUseCase {
    EProduct create(EProduct product);

    List<EProduct> findAll();

    EProduct updateCustomer(Integer id, EProduct dto);

    void deleteProduct(Integer id);

    List<EProduct> findByCategoryId(Integer categoryId);

    EProduct findById(Integer id);
}
