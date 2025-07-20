package com.fiap.fast_food_tc.application.usecase;
import com.fiap.fast_food_tc.domain.entity.Orders;
import java.util.List;

public interface OrdersUseCase {
    
    Short getNextOrderCode();

    List<Orders> getAllOrders();

    Orders create(Orders orderEntity);

    Orders getById(Integer id);

    Orders update(Integer id, Orders orderEntity);

    void delete(Integer id);
}
