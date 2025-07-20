package com.fiap.fast_food_tc.infrastructure.persistence.dataprovider;

import com.fiap.fast_food_tc.infrastructure.persistence.entity.OrderProductPersistenceEntity;
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
    public OrderProductPersistenceEntity create(OrderProductPersistenceEntity orderProductPersistenceEntity) {
        OrderProductPk pk = new OrderProductPk(orderProductPersistenceEntity.getOrdersPersistenceEntity().getOrderId(), orderProductPersistenceEntity.getProductPersistenceEntity().getProductId());
        orderProductPersistenceEntity.setId(pk);
        return repository.save(orderProductPersistenceEntity);
    }

    @Override
    public List<OrderProductPersistenceEntity> findByOrderId(Integer orderId) {
        return repository.findByOrdersPersistenceEntityOrderId(orderId);
    }

    @Override
    public List<OrderProductPersistenceEntity> getAll() {
        return repository.findAll();
    }

    @Override
    public OrderProductPersistenceEntity getById(OrderProductPk id) {
        return repository.findById(id).orElseThrow(() -> new IllegalArgumentException("OrderProduct not found"));
    }

    @Override
    public OrderProductPersistenceEntity update(OrderProductPersistenceEntity orderProductPersistenceEntity) {
        OrderProductPk pk = new OrderProductPk(orderProductPersistenceEntity.getOrdersPersistenceEntity().getOrderId(), orderProductPersistenceEntity.getProductPersistenceEntity().getProductId());
        OrderProductPersistenceEntity existing = repository.findById(pk).orElseThrow(() -> new IllegalArgumentException("Id not found"));
        existing.setProductQuantity(orderProductPersistenceEntity.getProductQuantity() != null ? orderProductPersistenceEntity.getProductQuantity() : existing.getProductQuantity());
        existing.setProductTotalAmount(orderProductPersistenceEntity.getProductTotalAmount() != null ? orderProductPersistenceEntity.getProductTotalAmount() : existing.getProductTotalAmount());
        return repository.save(existing);
    }

    @Override
    public void delete(OrderProductPk id) {
        repository.deleteById(id);
    }
}
