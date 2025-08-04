package com.fiap.fast_food_tc.application.usecase.impl;

import com.fiap.fast_food_tc.application.gateway.OrderProductGateway;
import com.fiap.fast_food_tc.application.gateway.ProductGateway;
import com.fiap.fast_food_tc.application.usecase.OrderProductUseCase;
import com.fiap.fast_food_tc.domain.entity.OrderProduct;
import com.fiap.fast_food_tc.domain.entity.Product;
import com.fiap.fast_food_tc.infrastructure.persistence.entity.ids.OrderProductPk;
import com.fiap.fast_food_tc.infrastructure.web.mapper.OrderProductMapper;
import com.fiap.fast_food_tc.infrastructure.web.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class OrderProductUseCaseImpl implements OrderProductUseCase {

    private final OrderProductGateway orderProductGateway;
    private final OrderProductMapper orderProductMapper;
    private final ProductGateway productGateway;
    private final ProductMapper productMapper;

    @Autowired
    public OrderProductUseCaseImpl(OrderProductGateway orderProductGateway, OrderProductMapper orderProductMapper,
                                   ProductGateway productGateway, ProductMapper productMapper) {
        this.orderProductGateway = orderProductGateway;
        this.orderProductMapper = orderProductMapper;
        this.productGateway = productGateway;
        this.productMapper = productMapper;
    }

    @Override
    public OrderProduct create(OrderProduct orderProduct) {
        Product product = productMapper.toEntity(productGateway.findById(orderProduct.getProductId()));
        BigDecimal totalAmount =
                product.getProductValue().multiply(BigDecimal.valueOf(orderProduct.getProductQuantity()));
        orderProduct.setProductTotalAmount(totalAmount);
        return orderProductMapper.toEntity(orderProductGateway.create(orderProductMapper.toModel(orderProduct)));
    }

    @Override
    public List<OrderProduct> getAll() {
        return orderProductMapper.toEntityList(orderProductGateway.getAll());
    }

    @Override
    public OrderProduct getById(Integer orderId, Integer productId) {
        OrderProductPk pk = new OrderProductPk(orderId, productId);
        return orderProductMapper.toEntity(orderProductGateway.getById(pk));
    }

    @Override
    public OrderProduct update(Integer orderId, Integer productId, OrderProduct orderProduct) {
        orderProduct.setOrderId(orderId);
        orderProduct.setProductId(productId);
        return orderProductMapper.toEntity(orderProductGateway.update(orderProductMapper.toModel(orderProduct)));
    }

    @Override
    public void delete(Integer orderId, Integer productId) {
        orderProductGateway.delete(new OrderProductPk(orderId, productId));
    }

    @Override
    public List<OrderProduct> getByOrderId(Integer orderId) {
        return orderProductMapper.toEntityList(orderProductGateway.findByOrderId(orderId));
    }
}
