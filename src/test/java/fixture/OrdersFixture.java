package fixture;

import com.fiap.fast_food_tc.domain.enums.StatusOrder;
import com.fiap.fast_food_tc.application.dto.orders.in.OrdersRequestDto;
import com.fiap.fast_food_tc.application.dto.orders.out.OrdersResponseDto;
import com.fiap.fast_food_tc.domain.entity.Orders;
import com.fiap.fast_food_tc.infrastructure.persistence.entity.OrdersPersistenceEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class OrdersFixture {

    public static Orders createEOrders() {
        return Orders.builder()
                .orderId(1)
                .orderDatetime(LocalDateTime.now())
                .statusOrder(StatusOrder.IN_PREPARATION)
                .orderCode((short) 1)
                .totalAmount(BigDecimal.ONE)
                .customerId(1)
                .build();
    }

    public static OrdersPersistenceEntity createOrders() {
        return OrdersPersistenceEntity.builder()
                .orderId(1)
                .orderDatetime(LocalDateTime.now())
                .statusOrder(StatusOrder.READY_FOR_PICKUP)
                .orderCode((short) 5)
                .totalAmount(BigDecimal.ONE)
                .customerPersistenceEntity(CustomerFixture.createCustomerModel())
                .build();
    }

    public static OrdersRequestDto createOrdersRequestDto() {
        return OrdersRequestDto.builder()
                .orderDatetime(LocalDateTime.now())
                .statusOrder(StatusOrder.IN_PREPARATION)
                .totalAmount(BigDecimal.TEN)
                .customerId(1)
                .build();
    }

    public static OrdersResponseDto createOrdersResponseDto() {
        return OrdersResponseDto.builder()
                .orderId(1)
                .orderDatetime(LocalDateTime.now())
                .statusOrder(StatusOrder.IN_PREPARATION)
                .orderCode((short) 5)
                .totalAmount(BigDecimal.ONE)
                .customerId(1)
                .build();
    }
}
