package com.fiap.fast_food_tc.domain.usecase;
import com.fiap.fast_food_tc.domain.entity.EOrders;
import java.util.List;

public interface OrdersUseCase {
    
    Short getNextOrderCode();


    List<EOrders> getAllOrders();

    EOrders create(EOrders orderEntity);
}
