package com.fiap.fast_food_tc.adapter.provider;

import com.fiap.fast_food_tc.adapter.db.model.Orders;
import com.fiap.fast_food_tc.adapter.db.repository.OrdersRepository;
import com.fiap.fast_food_tc.domain.gateway.OrdersGateway;
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
    public List<Orders> getAllOrders() {
        return repository.findAll();
    }

    @Override
    public Orders create(Orders model) {
        return repository.save(model);
    }

    @Override
    public Orders getById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Order not found"));
    }

    @Override
    public Orders update(Orders model) {
        Orders order = repository.findById(model.getOrderId()).orElseThrow(() -> new IllegalArgumentException("Id not found"));
        order.setOrderDatetime(model.getOrderDatetime() != null ? model.getOrderDatetime() : order.getOrderDatetime());
        order.setStatusOrder(model.getStatusOrder() != null ? model.getStatusOrder() : order.getStatusOrder());
        order.setTotalAmount(model.getTotalAmount() != null ? model.getTotalAmount() : order.getTotalAmount());
        order.setCustomer(model.getCustomer().getCustomerId() != null ? model.getCustomer() : order.getCustomer());
        return repository.save(order);
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }


    @Override
    public Short getLastOrderCode() {
        return repository.findFirstByOrderByOrderCodeDesc()
                .map(Orders::getOrderCode)
                .orElse((short) 0);
    }
}
