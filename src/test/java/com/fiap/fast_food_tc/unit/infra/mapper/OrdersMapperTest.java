package com.fiap.fast_food_tc.unit.infra.mapper;

import com.fiap.fast_food_tc.application.dto.orders.in.OrdersRequestDto;
import com.fiap.fast_food_tc.application.dto.orders.out.OrdersResponseDto;
import com.fiap.fast_food_tc.domain.entity.Orders;
import com.fiap.fast_food_tc.infrastructure.persistence.entity.OrdersPersistenceEntity;
import com.fiap.fast_food_tc.infrastructure.web.mapper.OrdersMapper;
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
        OrdersPersistenceEntity model = OrdersFixture.createOrders();

        Orders entity = mapper.toEntity(model);

        assertEquals(model.getOrderId(), entity.getOrderId());
        assertEquals(model.getOrderDatetime(), entity.getOrderDatetime());
        assertEquals(model.getStatusOrder(), entity.getStatusOrder());
        assertEquals(model.getOrderCode(), entity.getOrderCode());
        assertEquals(model.getTotalAmount(), entity.getTotalAmount());
        assertEquals(model.getCustomerPersistenceEntity().getCustomerId(), entity.getCustomerId());
    }

    @Test
    void toModelSuccess() {
        Orders entity = OrdersFixture.createEOrders();

        OrdersPersistenceEntity model = mapper.toModel(entity);

        assertEquals(entity.getOrderId(), model.getOrderId());
        assertEquals(entity.getOrderDatetime(), model.getOrderDatetime());
        assertEquals(entity.getStatusOrder(), model.getStatusOrder());
        assertEquals(entity.getOrderCode(), model.getOrderCode());
        assertEquals(entity.getTotalAmount(), model.getTotalAmount());
        assertEquals(entity.getCustomerId(), model.getCustomerPersistenceEntity().getCustomerId());
        assertNull(model.getOrderProductPersistenceEntities());
        assertNull(model.getPaymentPersistenceEntity());
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
        OrdersPersistenceEntity ordersPersistenceEntity = OrdersFixture.createOrders();

        List<Orders> entities = mapper.toEntityList(List.of(ordersPersistenceEntity));

        assertEquals(1, entities.size());
        assertEquals(ordersPersistenceEntity.getOrderId(), entities.getFirst().getOrderId());
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
