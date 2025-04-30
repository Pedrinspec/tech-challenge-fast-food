package com.fiap.fast_food_tc.domain.usecase;

import com.fiap.fast_food_tc.domain.entity.EProduct;

public interface ProductUseCase {

    EProduct create(EProduct product);

    EProduct update(EProduct product);

    void delete(Long id);


}
