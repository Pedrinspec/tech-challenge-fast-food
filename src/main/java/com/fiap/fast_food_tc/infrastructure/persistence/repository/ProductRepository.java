package com.fiap.fast_food_tc.infrastructure.persistence.repository;

import com.fiap.fast_food_tc.infrastructure.persistence.entity.ProductPersistenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductPersistenceEntity, Integer> {

    List<ProductPersistenceEntity> findByCategoryPersistenceEntityCategoryId(Integer categoryId);

    @Query("update ProductPersistenceEntity p set p.quantity = :newQuantity where productId = :id")
    @Transactional
    void substractQuantity(Integer id, Integer newQuantity);
}
