package com.fiap.fast_food_tc.app.dto.customer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerResponseDto {

    private Integer customerId;
    private String documentNumber;
    private String firstName;
    private String lastName;
    private String email;

}
