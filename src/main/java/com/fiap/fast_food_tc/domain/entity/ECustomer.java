package com.fiap.fast_food_tc.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ECustomer {

    private Long customerId;
    private String documentNumber;
    private String firstName;
    private String lastName;
    private String email;
}
