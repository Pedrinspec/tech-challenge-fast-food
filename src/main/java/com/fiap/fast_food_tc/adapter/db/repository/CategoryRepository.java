package com.fiap.fast_food_tc.adapter.db.repository;

import com.fiap.fast_food_tc.adapter.db.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
