package com.fiap.fast_food_tc.infrastructure.persistence.repository;

import com.fiap.fast_food_tc.infrastructure.persistence.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
