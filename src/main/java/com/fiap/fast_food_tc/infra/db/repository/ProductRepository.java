package com.fiap.fast_food_tc.infra.db.repository;

import com.fiap.fast_food_tc.infra.db.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    List<Product> findByCategoryCategoryId(Integer categoryId);

}
