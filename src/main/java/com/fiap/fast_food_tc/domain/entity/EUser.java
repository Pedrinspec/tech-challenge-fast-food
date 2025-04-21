package com.fiap.fast_food_tc.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EUser {

    UUID id;

    String cpf;

    String name;

    String email;

    String password;

}
