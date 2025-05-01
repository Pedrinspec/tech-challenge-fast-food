package com.fiap.fast_food_tc.domain.usecase;

import com.fiap.fast_food_tc.adapter.dto.ProductResponseDto;
import com.fiap.fast_food_tc.domain.entity.EProduct;

import java.util.List;

public interface ProductUseCase {

    EProduct create(EProduct product);

    EProduct update(EProduct product);

    void delete(Long id);

    List<EProduct> getByCategoryId(long categoryId);
}
