package com.fiap.fast_food_tc.adapter.dto.customer;

import com.fiap.fast_food_tc.adapter.db.model.Orders;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerResponseDto {

    private Long customerId;
    private String documentNumber;
    private String firstName;
    private String lastName;
    private String email;

    @Builder.Default
    private List<Orders> orders = new ArrayList<>();


}
