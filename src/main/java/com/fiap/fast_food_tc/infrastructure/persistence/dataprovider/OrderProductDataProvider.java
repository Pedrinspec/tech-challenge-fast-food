package com.fiap.fast_food_tc.infrastructure.persistence.dataprovider;

import com.fiap.fast_food_tc.infrastructure.persistence.entity.OrderProduct;
import com.fiap.fast_food_tc.infrastructure.persistence.entity.ids.OrderProductPk;
import com.fiap.fast_food_tc.infrastructure.persistence.repository.OrderProductRepository;
import com.fiap.fast_food_tc.application.gateway.OrderProductGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderProductDataProvider implements OrderProductGateway {

    private final OrderProductRepository repository;

    @Autowired
    public OrderProductDataProvider(OrderProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public OrderProduct create(OrderProduct orderProduct) {
        OrderProductPk pk = new OrderProductPk(orderProduct.getOrders().getOrderId(), orderProduct.getProduct().getProductId());
        orderProduct.setId(pk);
        return repository.save(orderProduct);
    }

    @Override
    public List<OrderProduct> findByOrderId(Integer orderId) {
        return repository.findByOrdersOrderId(orderId);
    }

    @Override
    public List<OrderProduct> getAll() {
        return repository.findAll();
    }

    @Override
    public OrderProduct getById(OrderProductPk id) {
        return repository.findById(id).orElseThrow(() -> new IllegalArgumentException("OrderProduct not found"));
    }

    @Override
    public OrderProduct update(OrderProduct orderProduct) {
        OrderProductPk pk = new OrderProductPk(orderProduct.getOrders().getOrderId(), orderProduct.getProduct().getProductId());
        OrderProduct existing = repository.findById(pk).orElseThrow(() -> new IllegalArgumentException("Id not found"));
        existing.setProductQuantity(orderProduct.getProductQuantity() != null ? orderProduct.getProductQuantity() : existing.getProductQuantity());
        existing.setProductTotalAmount(orderProduct.getProductTotalAmount() != null ? orderProduct.getProductTotalAmount() : existing.getProductTotalAmount());
        return repository.save(existing);
    }

    @Override
    public void delete(OrderProductPk id) {
        repository.deleteById(id);
    }
}
