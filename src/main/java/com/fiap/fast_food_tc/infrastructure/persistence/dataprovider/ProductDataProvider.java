package com.fiap.fast_food_tc.infrastructure.persistence.dataprovider;

import com.fiap.fast_food_tc.infrastructure.persistence.entity.ProductPersistenceEntity;
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
    public ProductPersistenceEntity create(ProductPersistenceEntity productPersistenceEntity) {
        return productRepository.save(productPersistenceEntity);
    }

    @Override
    public List<ProductPersistenceEntity> findAll() {
        return productRepository.findAll();
    }

    @Override
    public ProductPersistenceEntity update(ProductPersistenceEntity productPersistenceEntity) {
        return productRepository.save(productPersistenceEntity);
    }

    @Override
    public void delete(Integer id) {
        productRepository.deleteById(id);
    }

    @Override
    public ProductPersistenceEntity findById(Integer id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
    }

    @Override
    public List<ProductPersistenceEntity> findByCategoryId(Integer categoryId) {
        return productRepository.findByCategoryPersistenceEntityCategoryId(categoryId);
    }

    @Override
    public void substractQuantity(Integer id, Integer newQuantity) {
        productRepository.substractQuantity(id, newQuantity);
    }

}
