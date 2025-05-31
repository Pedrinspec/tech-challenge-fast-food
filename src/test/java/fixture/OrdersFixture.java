package fixture;

import com.fiap.fast_food_tc.adapter.db.model.Orders;
import com.fiap.fast_food_tc.adapter.dto.orders.OrdersRequestDto;
import com.fiap.fast_food_tc.adapter.dto.orders.OrdersResponseDto;
import com.fiap.fast_food_tc.domain.entity.EOrders;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class OrdersFixture {

    public static EOrders createEOrders() {
        return EOrders.builder()
                .orderId(1)
                .orderDatetime(LocalDateTime.now())
                .statusOrder(1)
                .orderCode((short) 1)
                .totalAmount(BigDecimal.ONE)
                .customerId(1)
                .build();
    }

    public static Orders createOrders() {
        return Orders.builder()
                .orderId(1)
                .orderDatetime(LocalDateTime.now())
                .statusOrder(2)
                .orderCode((short) 5)
                .totalAmount(BigDecimal.ONE)
                .customer(CustomerFixture.createCustomerModel())
                .build();
    }

    public static OrdersRequestDto createOrdersRequestDto() {
        return OrdersRequestDto.builder()
                .orderDatetime(LocalDateTime.now())
                .statusOrder(1)
                .totalAmount(BigDecimal.TEN)
                .customerId(1)
                .build();
    }

    public static OrdersResponseDto createOrdersResponseDto() {
        return OrdersResponseDto.builder()
                .orderId(1)
                .orderDatetime(LocalDateTime.now())
                .statusOrder(2)
                .orderCode((short) 5)
                .totalAmount(BigDecimal.ONE)
                .customerId(1)
                .build();
    }
}
