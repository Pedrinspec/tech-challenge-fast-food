package com.fiap.fast_food_tc.domain.entity;

import com.fiap.fast_food_tc.adapter.db.model.Order;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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
    private List<Order> orders;

}
