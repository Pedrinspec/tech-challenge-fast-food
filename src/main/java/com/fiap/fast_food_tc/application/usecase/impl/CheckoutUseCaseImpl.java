package com.fiap.fast_food_tc.application.usecase.impl;

import com.fiap.fast_food_tc.domain.enums.StatusOrder;
import com.fiap.fast_food_tc.domain.entity.ECheckout;
import com.fiap.fast_food_tc.domain.entity.ECheckoutOrder;
import com.fiap.fast_food_tc.domain.entity.EOrderProduct;
import com.fiap.fast_food_tc.domain.entity.EOrders;
import com.fiap.fast_food_tc.domain.entity.EProduct;
import com.fiap.fast_food_tc.application.gateway.CheckoutGateway;
import com.fiap.fast_food_tc.application.usecase.CheckoutUseCase;
import com.fiap.fast_food_tc.application.usecase.OrderProductUseCase;
import com.fiap.fast_food_tc.application.usecase.OrdersUseCase;
import com.fiap.fast_food_tc.application.usecase.ProductUseCase;
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
    @Transactional
    public ECheckout checkoutAndCreateOrder(ECheckoutOrder request) {
        EOrders order = EOrders.builder()
                .orderDatetime(java.time.LocalDateTime.now())
                .statusOrder(StatusOrder.PAYMENT_PENDING)
                .totalAmount(BigDecimal.ZERO)
                .customerId(request.getCustomerId())
                .orderCode(ordersUseCase.getNextOrderCode())
                .build();

        order = ordersUseCase.create(order);

        BigDecimal total = BigDecimal.ZERO;

        for (ECheckoutOrder.Item item : request.getItems()) {
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
        String paymentLink = checkoutGateway.getPaymentLink(order.getOrderId());
        return buildCheckout(total, order, paymentLink, request);
    }

    private ECheckout buildCheckout(BigDecimal total, EOrders order, String paymentLink, ECheckoutOrder request) {
        return ECheckout.builder()
                .totalAmount(total)
                .orderId(order.getOrderId())
                .orderCode(order.getOrderCode())
                .orderRequest(request)
                .paymentLink(paymentLink)
                .statusOrder(order.getStatusOrder())
                .build();
    }


    @Override
    public void handleWebhook(String paymentId) {
        checkoutGateway.verifyApprovedPayment(paymentId);
    }

}
