package com.fiap.fast_food_tc.adapter.controller;

import com.fiap.fast_food_tc.adapter.dto.UserRequestDto;
import com.fiap.fast_food_tc.adapter.dto.UserResponseDto;
import com.fiap.fast_food_tc.app.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserResponseDto> createUser(@RequestBody UserRequestDto user) {
        var userCreated = userService.create(user);
        return ResponseEntity.created(URI.create("/user/" + userCreated.getId())).body(userCreated);
    }


}
