package com.fiap.fast_food_tc.unit.cross.mapper;

import com.fiap.fast_food_tc.infra.db.model.OrderProduct;
import com.fiap.fast_food_tc.app.dto.orderproduct.OrderProductRequestDto;
import com.fiap.fast_food_tc.app.dto.orderproduct.OrderProductResponseDto;
import com.fiap.fast_food_tc.cross.mapper.OrderProductMapper;
import com.fiap.fast_food_tc.domain.entity.EOrderProduct;
import fixture.OrderProductFixture;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderProductMapperTest {

    private final OrderProductMapper mapper = Mappers.getMapper(OrderProductMapper.class);

    @Test
    void toModelSuccess() {
        EOrderProduct entity = OrderProductFixture.createEOrderProduct();

        OrderProduct model = mapper.toModel(entity);

        assertEquals(entity.getOrderId(), model.getOrders().getOrderId());
        assertEquals(entity.getProductId(), model.getProduct().getProductId());
        assertEquals(entity.getProductQuantity(), model.getProductQuantity());
        assertEquals(entity.getProductTotalAmount(), model.getProductTotalAmount());
        assertNull(model.getId());
    }

    @Test
    void toEntitySuccess() {
        OrderProduct model = OrderProductFixture.createOrderProduct();

        EOrderProduct entity = mapper.toEntity(model);

        assertEquals(model.getOrders().getOrderId(), entity.getOrderId());
        assertEquals(model.getProduct().getProductId(), entity.getProductId());
        assertEquals(model.getProductQuantity(), entity.getProductQuantity());
        assertEquals(model.getProductTotalAmount(), entity.getProductTotalAmount());
    }

    @Test
    void toEntityCreateSuccess() {
        OrderProductRequestDto request = OrderProductFixture.createRequest();

        EOrderProduct entity = mapper.toEntityCreate(request);

        assertEquals(request.getOrderId(), entity.getOrderId());
        assertEquals(request.getProductId(), entity.getProductId());
        assertEquals(request.getProductQuantity(), entity.getProductQuantity());
        assertEquals(request.getProductTotalAmount(), entity.getProductTotalAmount());
    }

    @Test
    void toResponseSuccess() {
        EOrderProduct entity = OrderProductFixture.createEOrderProduct();

        OrderProductResponseDto dto = mapper.toResponse(entity);

        assertEquals(entity.getOrderId(), dto.getOrderId());
        assertEquals(entity.getProductId(), dto.getProductId());
        assertEquals(entity.getProductQuantity(), dto.getProductQuantity());
        assertEquals(entity.getProductTotalAmount(), dto.getProductTotalAmount());
    }

    @Test
    void toResponseListSuccess() {
        EOrderProduct entity = OrderProductFixture.createEOrderProduct();

        List<OrderProductResponseDto> list = mapper.toResponseList(List.of(entity));

        assertEquals(1, list.size());
        assertEquals(entity.getOrderId(), list.getFirst().getOrderId());
    }

    @Test
    void toEntityListSuccess() {
        OrderProduct model = OrderProductFixture.createOrderProduct();

        List<EOrderProduct> list = mapper.toEntityList(List.of(model));

        assertEquals(1, list.size());
        assertEquals(model.getOrders().getOrderId(), list.getFirst().getOrderId());
    }
}
