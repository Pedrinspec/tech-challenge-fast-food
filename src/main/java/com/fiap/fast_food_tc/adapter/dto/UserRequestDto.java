package com.fiap.fast_food_tc.adapter.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDto {

    private String cpf;

    private String name;

    private String email;

    private String password;

}
