package fixture;

import com.fiap.fast_food_tc.infrastructure.persistence.entity.Customer;
import com.fiap.fast_food_tc.application.dto.customer.CustomerRequestDto;
import com.fiap.fast_food_tc.application.dto.customer.CustomerResponseDto;
import com.fiap.fast_food_tc.domain.entity.ECustomer;

import java.util.List;

public class CustomerFixture {

    public static Customer createCustomerModel() {
        return Customer.builder()
                .customerId(1)
                .documentNumber("111111")
                .firstName("Joao")
                .lastName("Silva")
                .email("joao@email.com")
                .orders(List.of())
                .build();
    }

    public static ECustomer createECustomer() {
        return ECustomer.builder()
                .customerId(1)
                .documentNumber("111111")
                .firstName("Joao")
                .lastName("Silva")
                .email("joao@email.com")
                .build();
    }

    public static CustomerRequestDto createCustomerRequestDto() {
        return CustomerRequestDto.builder()
                .documentNumber("111111")
                .firstName("Joao")
                .lastName("Silva")
                .email("joao@email.com")
                .build();
    }

    public static CustomerResponseDto createCustomerResponseDto() {
        return CustomerResponseDto.builder()
                .customerId(1)
                .documentNumber("111111")
                .firstName("Joao")
                .lastName("Silva")
                .email("joao@email.com")
                .build();
    }
}
