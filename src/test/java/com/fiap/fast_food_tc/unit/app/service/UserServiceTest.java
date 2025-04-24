package com.fiap.fast_food_tc.unit.app.service;

import com.fiap.fast_food_tc.adapter.dto.UserRequestDto;
import com.fiap.fast_food_tc.app.service.UserService;
import com.fiap.fast_food_tc.cross.UserMapper;
import com.fiap.fast_food_tc.domain.entity.EUser;
import com.fiap.fast_food_tc.domain.usecase.UserUseCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserUseCase userUseCase;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserService service;

    @Test
    void shouldCreateUserSuccess() {
        var requestDto = new UserRequestDto("111111", "João", "joao@email.com", "1234");
        var userSalvo = new EUser(1L, "111111", "João", "joao@email.com", "1234");

        when(userUseCase.create(any(EUser.class))).thenReturn(userSalvo);

        var response = service.create(requestDto);

        assertEquals("João", response.getName());
        assertEquals("joao@email.com", response.getEmail());
        assertNotNull(response.getId());
        verify(userUseCase).create(any(EUser.class));
    }

}
