package com.fiap.fast_food_tc.adapter.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerRequestDto {

    private String documentNumber;
    private String firstName;
    private String lastName;
    private String email;

}
