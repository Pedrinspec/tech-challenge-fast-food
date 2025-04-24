package com.fiap.fast_food_tc.domain.gateway;

import com.fiap.fast_food_tc.adapter.db.model.User;

public interface UserGateway {
    User create(User domain);
}
