package com.fiap.fast_food_tc.infrastructure.persistence.dataprovider;

import com.fiap.fast_food_tc.infrastructure.persistence.entity.OrdersPersistenceEntity;
import com.fiap.fast_food_tc.infrastructure.persistence.repository.OrdersRepository;
import com.fiap.fast_food_tc.application.gateway.OrdersGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrdersDataProvider implements OrdersGateway {

    private final OrdersRepository repository;

    @Autowired
    public OrdersDataProvider(OrdersRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<OrdersPersistenceEntity> getAllOrders() {
        return repository.findAll();
    }

    @Override
    public OrdersPersistenceEntity create(OrdersPersistenceEntity model) {
        return repository.save(model);
    }

    @Override
    public OrdersPersistenceEntity getById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Order not found"));
    }

    @Override
    public OrdersPersistenceEntity update(OrdersPersistenceEntity model) {
        OrdersPersistenceEntity order = repository.findById(model.getOrderId()).orElseThrow(() -> new IllegalArgumentException("Id not found"));
        order.setOrderDatetime(model.getOrderDatetime() != null ? model.getOrderDatetime() : order.getOrderDatetime());
        order.setStatusOrder(model.getStatusOrder() != null ? model.getStatusOrder() : order.getStatusOrder());
        order.setTotalAmount(model.getTotalAmount() != null ? model.getTotalAmount() : order.getTotalAmount());
        order.setCustomerPersistenceEntity(model.getCustomerPersistenceEntity().getCustomerId() != null ? model.getCustomerPersistenceEntity() : order.getCustomerPersistenceEntity());
        return repository.save(order);
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }


    @Override
    public Short getLastOrderCode() {
        return repository.findFirstByOrderByOrderCodeDesc()
                .map(OrdersPersistenceEntity::getOrderCode)
                .orElse((short) 0);
    }
}
