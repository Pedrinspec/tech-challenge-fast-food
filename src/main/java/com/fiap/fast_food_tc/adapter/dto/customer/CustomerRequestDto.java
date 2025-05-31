package com.fiap.fast_food_tc.adapter.dto.customer;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerRequestDto {

    @CPF(message = "CPF inválido")
    private String documentNumber;

    private String firstName;
    private String lastName;

    @NotBlank(message = "Email é obrigatório")
    @Email(message = "Email inválido")
    private String email;

}
