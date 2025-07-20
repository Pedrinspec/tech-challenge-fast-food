package com.fiap.fast_food_tc.unit.cross.mapper;

import com.fiap.fast_food_tc.application.dto.orders.OrdersRequestDto;
import com.fiap.fast_food_tc.application.dto.orders.OrdersResponseDto;
import com.fiap.fast_food_tc.domain.entity.Orders;
import com.fiap.fast_food_tc.infrastructure.web.rest.mapper.OrdersMapper;
import fixture.OrdersFixture;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrdersMapperTest {

    private final OrdersMapper mapper = Mappers.getMapper(OrdersMapper.class);

    @Test
    void toEntityCreateSuccess() {
        OrdersRequestDto request = OrdersFixture.createOrdersRequestDto();

        Orders entity = mapper.toEntityCreate(request);

        assertNull(entity.getOrderId());
        assertNull(entity.getOrderCode());
        assertEquals(request.getOrderDatetime(), entity.getOrderDatetime());
        assertEquals(request.getStatusOrder(), entity.getStatusOrder());
        assertEquals(request.getTotalAmount(), entity.getTotalAmount());
        assertEquals(request.getCustomerId(), entity.getCustomerId());
    }

    @Test
    void toEntitySuccess() {
        com.fiap.fast_food_tc.infrastructure.persistence.entity.Orders model = OrdersFixture.createOrders();

        Orders entity = mapper.toEntity(model);

        assertEquals(model.getOrderId(), entity.getOrderId());
        assertEquals(model.getOrderDatetime(), entity.getOrderDatetime());
        assertEquals(model.getStatusOrder(), entity.getStatusOrder());
        assertEquals(model.getOrderCode(), entity.getOrderCode());
        assertEquals(model.getTotalAmount(), entity.getTotalAmount());
        assertEquals(model.getCustomer().getCustomerId(), entity.getCustomerId());
    }

    @Test
    void toModelSuccess() {
        Orders entity = OrdersFixture.createEOrders();

        com.fiap.fast_food_tc.infrastructure.persistence.entity.Orders model = mapper.toModel(entity);

        assertEquals(entity.getOrderId(), model.getOrderId());
        assertEquals(entity.getOrderDatetime(), model.getOrderDatetime());
        assertEquals(entity.getStatusOrder(), model.getStatusOrder());
        assertEquals(entity.getOrderCode(), model.getOrderCode());
        assertEquals(entity.getTotalAmount(), model.getTotalAmount());
        assertEquals(entity.getCustomerId(), model.getCustomer().getCustomerId());
        assertNull(model.getOrderProducts());
        assertNull(model.getPayment());
    }

    @Test
    void toResponseListSuccess() {
        Orders orders = OrdersFixture.createEOrders();

        List<OrdersResponseDto> dtos = mapper.toResponseList(List.of(orders));

        assertEquals(1, dtos.size());
        assertEquals(orders.getOrderId(), dtos.getFirst().getOrderId());
    }

    @Test
    void toEntityListSuccess() {
        com.fiap.fast_food_tc.infrastructure.persistence.entity.Orders orders = OrdersFixture.createOrders();

        List<Orders> entities = mapper.toEntityList(List.of(orders));

        assertEquals(1, entities.size());
        assertEquals(orders.getOrderId(), entities.getFirst().getOrderId());
    }

    @Test
    void toResponseSuccess() {
        Orders entity = OrdersFixture.createEOrders();

        OrdersResponseDto dto = mapper.toResponse(entity);

        assertEquals(entity.getOrderId(), dto.getOrderId());
        assertEquals(entity.getOrderDatetime(), dto.getOrderDatetime());
        assertEquals(entity.getStatusOrder(), dto.getStatusOrder());
        assertEquals(entity.getOrderCode(), dto.getOrderCode());
        assertEquals(entity.getTotalAmount(), dto.getTotalAmount());
        assertEquals(entity.getCustomerId(), dto.getCustomerId());
    }
}
