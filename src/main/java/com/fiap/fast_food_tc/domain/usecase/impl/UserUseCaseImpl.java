package com.fiap.fast_food_tc.domain.usecase.impl;

import com.fiap.fast_food_tc.cross.UserMapper;
import com.fiap.fast_food_tc.domain.entity.EUser;
import com.fiap.fast_food_tc.domain.gateway.UserGateway;
import com.fiap.fast_food_tc.domain.usecase.UserUseCase;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserUseCaseImpl implements UserUseCase {

    private final UserGateway userGateway;

    private final UserMapper userMapper;


    public UserUseCaseImpl(UserGateway userGateway, UserMapper userMapper) {
        this.userGateway = userGateway;
        this.userMapper = userMapper;
    }


    @Override
    public EUser create(EUser eUser) {
        return userMapper.toEntity(userGateway.create(userMapper.toDomain(eUser)));
    }

    @Override
    public void update(EUser user) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public EUser getById(Long id) {
        return null;
    }

    @Override
    public List<EUser> getAll() {
        return List.of();
    }
}
