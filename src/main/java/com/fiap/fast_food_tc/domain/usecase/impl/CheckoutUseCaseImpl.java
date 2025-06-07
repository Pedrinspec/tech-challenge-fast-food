package com.fiap.fast_food_tc.domain.usecase.impl;

import com.fiap.fast_food_tc.app.dto.checkout.CheckoutOrderRequest;
import com.fiap.fast_food_tc.domain.entity.EOrderProduct;
import com.fiap.fast_food_tc.domain.entity.EOrders;
import com.fiap.fast_food_tc.domain.entity.EProduct;
import com.fiap.fast_food_tc.domain.gateway.CheckoutGateway;
import com.fiap.fast_food_tc.domain.usecase.CheckoutUseCase;
import com.fiap.fast_food_tc.domain.usecase.OrderProductUseCase;
import com.fiap.fast_food_tc.domain.usecase.OrdersUseCase;
import com.fiap.fast_food_tc.domain.usecase.ProductUseCase;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class CheckoutUseCaseImpl implements CheckoutUseCase {

    private final CheckoutGateway checkoutGateway;
    private final OrdersUseCase ordersUseCase;
    private final OrderProductUseCase orderProductUseCase;
    private final ProductUseCase productUseCase;

    @Autowired
    public CheckoutUseCaseImpl(CheckoutGateway checkoutGateway,
                               OrdersUseCase ordersUseCase,
                               OrderProductUseCase orderProductUseCase,
                               ProductUseCase productUseCase) {
        this.checkoutGateway = checkoutGateway;
        this.ordersUseCase = ordersUseCase;
        this.orderProductUseCase = orderProductUseCase;
        this.productUseCase = productUseCase;
    }


    @Override
    public String getPaymentLink(Integer orderId) {
        return checkoutGateway.getPaymentLink(orderId);
    }

    @Override
    @Transactional
    public String checkoutAndCreateOrder(CheckoutOrderRequest request) {
        EOrders order = EOrders.builder()
                .orderDatetime(java.time.LocalDateTime.now())
                .statusOrder(1)
                .totalAmount(java.math.BigDecimal.ZERO)
                .customerId(request.getCustomerId())
                .orderCode(ordersUseCase.getNextOrderCode())
                .build();

        order = ordersUseCase.create(order);

        BigDecimal total = BigDecimal.ZERO;

        for (CheckoutOrderRequest.Item item : request.getItems()) {
            EProduct product = productUseCase.findById(item.getProductId());
            BigDecimal itemTotal = product.getProductValue()
                    .multiply(BigDecimal.valueOf(item.getQuantity()));
            total = total.add(itemTotal);

            EOrderProduct op = EOrderProduct.builder()
                    .orderId(order.getOrderId())
                    .productId(product.getProductId())
                    .productQuantity(item.getQuantity())
                    .productTotalAmount(itemTotal)
                    .build();
            orderProductUseCase.create(op);
        }

        order.setTotalAmount(total);
        ordersUseCase.update(order.getOrderId(), order);

        return checkoutGateway.getPaymentLink(order.getOrderId());
    }

    @Override
    public void handleWebhook(String paymentId) {
        checkoutGateway.verifyApprovedPayment(paymentId);
    }

}
