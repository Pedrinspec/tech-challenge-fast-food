package com.fiap.fast_food_tc.application.gateway;

import com.fiap.fast_food_tc.infrastructure.persistence.entity.ProductPersistenceEntity;

import java.util.List;

public interface ProductGateway {
    ProductPersistenceEntity create(ProductPersistenceEntity eProductPersistenceEntity);

    List<ProductPersistenceEntity> findAll();

    ProductPersistenceEntity update(ProductPersistenceEntity productPersistenceEntity);

    void delete(Integer id);

    ProductPersistenceEntity findById(Integer id);

    List<ProductPersistenceEntity> findByCategoryId(Integer categoryId);

}
