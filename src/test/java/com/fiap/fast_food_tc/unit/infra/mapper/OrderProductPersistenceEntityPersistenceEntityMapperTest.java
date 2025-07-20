package com.fiap.fast_food_tc.unit.infra.mapper;

import com.fiap.fast_food_tc.application.dto.orderproduct.OrderProductRequestDto;
import com.fiap.fast_food_tc.application.dto.orderproduct.OrderProductResponseDto;
import com.fiap.fast_food_tc.infrastructure.persistence.entity.OrderProductPersistenceEntity;
import com.fiap.fast_food_tc.infrastructure.web.rest.mapper.OrderProductMapper;
import com.fiap.fast_food_tc.domain.entity.OrderProduct;
import fixture.OrderProductFixture;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderProductPersistenceEntityPersistenceEntityMapperTest {

    private final OrderProductMapper mapper = Mappers.getMapper(OrderProductMapper.class);

    @Test
    void toModelSuccess() {
        OrderProduct entity = OrderProductFixture.createEOrderProduct();

        OrderProductPersistenceEntity model = mapper.toModel(entity);

        assertEquals(entity.getOrderId(), model.getOrdersPersistenceEntity().getOrderId());
        assertEquals(entity.getProductId(), model.getProductPersistenceEntity().getProductId());
        assertEquals(entity.getProductQuantity(), model.getProductQuantity());
        assertEquals(entity.getProductTotalAmount(), model.getProductTotalAmount());
        assertNull(model.getId());
    }

    @Test
    void toEntitySuccess() {
        OrderProductPersistenceEntity model = OrderProductFixture.createOrderProduct();

        OrderProduct entity = mapper.toEntity(model);

        assertEquals(model.getOrdersPersistenceEntity().getOrderId(), entity.getOrderId());
        assertEquals(model.getProductPersistenceEntity().getProductId(), entity.getProductId());
        assertEquals(model.getProductQuantity(), entity.getProductQuantity());
        assertEquals(model.getProductTotalAmount(), entity.getProductTotalAmount());
    }

    @Test
    void toEntityCreateSuccess() {
        OrderProductRequestDto request = OrderProductFixture.createRequest();

        OrderProduct entity = mapper.toEntityCreate(request);

        assertEquals(request.getOrderId(), entity.getOrderId());
        assertEquals(request.getProductId(), entity.getProductId());
        assertEquals(request.getProductQuantity(), entity.getProductQuantity());
        assertEquals(request.getProductTotalAmount(), entity.getProductTotalAmount());
    }

    @Test
    void toResponseSuccess() {
        OrderProduct entity = OrderProductFixture.createEOrderProduct();

        OrderProductResponseDto dto = mapper.toResponse(entity);

        assertEquals(entity.getOrderId(), dto.getOrderId());
        assertEquals(entity.getProductId(), dto.getProductId());
        assertEquals(entity.getProductQuantity(), dto.getProductQuantity());
        assertEquals(entity.getProductTotalAmount(), dto.getProductTotalAmount());
    }

    @Test
    void toResponseListSuccess() {
        OrderProduct entity = OrderProductFixture.createEOrderProduct();

        List<OrderProductResponseDto> list = mapper.toResponseList(List.of(entity));

        assertEquals(1, list.size());
        assertEquals(entity.getOrderId(), list.getFirst().getOrderId());
    }

    @Test
    void toEntityListSuccess() {
        OrderProductPersistenceEntity model = OrderProductFixture.createOrderProduct();

        List<OrderProduct> list = mapper.toEntityList(List.of(model));

        assertEquals(1, list.size());
        assertEquals(model.getOrdersPersistenceEntity().getOrderId(), list.getFirst().getOrderId());
    }
}
