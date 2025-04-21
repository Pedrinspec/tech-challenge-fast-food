package com.fiap.fast_food_tc.app.service;

import com.fiap.fast_food_tc.adapter.dto.UserRequestDto;
import com.fiap.fast_food_tc.adapter.dto.UserResponseDto;
import com.fiap.fast_food_tc.cross.UserMapper;
import com.fiap.fast_food_tc.domain.usecase.UserUseCase;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserUseCase userUseCase;

    private final UserMapper userMapper;

    public UserService(UserUseCase userUseCase, UserMapper userMapper) {
        this.userUseCase = userUseCase;
        this.userMapper = userMapper;
    }

    public UserResponseDto create(UserRequestDto user) {
        return userMapper.entityToMessage(userUseCase.create(userMapper.messageToEntity(user)));
    }

}
