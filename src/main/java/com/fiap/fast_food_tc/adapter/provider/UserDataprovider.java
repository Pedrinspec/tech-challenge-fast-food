package com.fiap.fast_food_tc.adapter.provider;

import com.fiap.fast_food_tc.adapter.db.model.User;
import com.fiap.fast_food_tc.adapter.db.repository.UserRepository;
import com.fiap.fast_food_tc.domain.gateway.UserGateway;
import org.springframework.stereotype.Component;

@Component
public class UserDataprovider implements UserGateway {

    private final UserRepository repository;

    public UserDataprovider(UserRepository repository) {
        this.repository = repository;
    }

    public User create(User user) {
        return repository.save(user);
    }

}
