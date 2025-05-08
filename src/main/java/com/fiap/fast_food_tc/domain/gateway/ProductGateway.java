package com.fiap.fast_food_tc.domain.gateway;

import com.fiap.fast_food_tc.adapter.db.model.Product;

public interface ProductGateway {
    Product create(Product eProduct);
}
