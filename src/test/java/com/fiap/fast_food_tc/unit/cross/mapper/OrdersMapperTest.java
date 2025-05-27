package com.fiap.fast_food_tc.unit.cross.mapper;

import com.fiap.fast_food_tc.adapter.db.model.Customer;
import com.fiap.fast_food_tc.adapter.db.model.Orders;
import com.fiap.fast_food_tc.adapter.dto.orders.OrdersRequestDto;
import com.fiap.fast_food_tc.adapter.dto.orders.OrdersResponseDto;
import com.fiap.fast_food_tc.cross.mapper.OrdersMapper;
import com.fiap.fast_food_tc.domain.entity.EOrders;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrdersMapperTest {

    private final OrdersMapper mapper = Mappers.getMapper(OrdersMapper.class);

    @Test
    void toEntityCreateSuccess() {
        OrdersRequestDto request = OrdersRequestDto.builder()
                .orderDatetime(LocalDateTime.now())
                .statusOrder(1)
                .totalAmount(BigDecimal.TEN)
                .customerId(1)
                .build();

        EOrders entity = mapper.toEntityCreate(request);

        assertNull(entity.getOrderId());
        assertNull(entity.getOrderCode());
        assertEquals(request.getOrderDatetime(), entity.getOrderDatetime());
        assertEquals(request.getStatusOrder(), entity.getStatusOrder());
        assertEquals(request.getTotalAmount(), entity.getTotalAmount());
        assertEquals(request.getCustomerId(), entity.getCustomerId());
    }

    @Test
    void toEntitySuccess() {
        Customer customer = Customer.builder().customerId(2).build();
        Orders model = Orders.builder()
                .orderId(1)
                .orderDatetime(LocalDateTime.now())
                .statusOrder(2)
                .orderCode((short) 5)
                .totalAmount(BigDecimal.ONE)
                .customer(customer)
                .build();

        EOrders entity = mapper.toEntity(model);

        assertEquals(model.getOrderId(), entity.getOrderId());
        assertEquals(model.getOrderDatetime(), entity.getOrderDatetime());
        assertEquals(model.getStatusOrder(), entity.getStatusOrder());
        assertEquals(model.getOrderCode(), entity.getOrderCode());
        assertEquals(model.getTotalAmount(), entity.getTotalAmount());
        assertNull(entity.getCustomerId());
    }

    @Test
    void toModelSuccess() {
        EOrders entity = EOrders.builder()
                .orderId(1)
                .orderDatetime(LocalDateTime.now())
                .statusOrder(2)
                .orderCode((short) 5)
                .totalAmount(BigDecimal.ONE)
                .customerId(3)
                .build();

        Orders model = mapper.toModel(entity);

        assertEquals(entity.getOrderId(), model.getOrderId());
        assertEquals(entity.getOrderDatetime(), model.getOrderDatetime());
        assertEquals(entity.getStatusOrder(), model.getStatusOrder());
        assertEquals(entity.getOrderCode(), model.getOrderCode());
        assertEquals(entity.getTotalAmount(), model.getTotalAmount());
        assertNull(model.getCustomer());
        assertNull(model.getOrderProducts());
        assertNull(model.getPayment());
    }

    @Test
    void toResponseListSuccess() {
        EOrders eOrders = EOrders.builder()
                .orderId(1)
                .orderDatetime(LocalDateTime.now())
                .statusOrder(2)
                .orderCode((short) 5)
                .totalAmount(BigDecimal.ONE)
                .customerId(3)
                .build();

        List<OrdersResponseDto> dtos = mapper.toResponseList(List.of(eOrders));

        assertEquals(1, dtos.size());
        assertEquals(eOrders.getOrderId(), dtos.get(0).getOrderId());
    }

    @Test
    void toEntityListSuccess() {
        Orders orders = Orders.builder()
                .orderId(1)
                .orderDatetime(LocalDateTime.now())
                .statusOrder(2)
                .orderCode((short) 5)
                .totalAmount(BigDecimal.ONE)
                .build();

        List<EOrders> entities = mapper.toEntityList(List.of(orders));

        assertEquals(1, entities.size());
        assertEquals(orders.getOrderId(), entities.get(0).getOrderId());
    }

    @Test
    void toResponseSuccess() {
        EOrders entity = EOrders.builder()
                .orderId(1)
                .orderDatetime(LocalDateTime.now())
                .statusOrder(2)
                .orderCode((short)5)
                .totalAmount(BigDecimal.ONE)
                .customerId(3)
                .build();

        OrdersResponseDto dto = mapper.toResponse(entity);

        assertEquals(entity.getOrderId(), dto.getOrderId());
        assertEquals(entity.getOrderDatetime(), dto.getOrderDatetime());
        assertEquals(entity.getStatusOrder(), dto.getStatusOrder());
        assertEquals(entity.getOrderCode(), dto.getOrderCode());
        assertEquals(entity.getTotalAmount(), dto.getTotalAmount());
        assertEquals(entity.getCustomerId(), dto.getCustomerId());
    }
}
