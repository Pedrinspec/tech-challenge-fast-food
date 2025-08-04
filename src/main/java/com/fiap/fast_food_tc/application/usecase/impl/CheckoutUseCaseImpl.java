package com.fiap.fast_food_tc.application.usecase.impl;

import com.fiap.fast_food_tc.application.dto.checkout.out.MPPaymentResponse;
import com.fiap.fast_food_tc.application.gateway.CheckoutGateway;
import com.fiap.fast_food_tc.application.gateway.PaymentGateway;
import com.fiap.fast_food_tc.application.usecase.CheckoutUseCase;
import com.fiap.fast_food_tc.application.usecase.OrderProductUseCase;
import com.fiap.fast_food_tc.application.usecase.OrdersUseCase;
import com.fiap.fast_food_tc.application.usecase.ProductUseCase;
import com.fiap.fast_food_tc.domain.entity.Checkout;
import com.fiap.fast_food_tc.domain.entity.CheckoutOrder;
import com.fiap.fast_food_tc.domain.entity.OrderProduct;
import com.fiap.fast_food_tc.domain.entity.Orders;
import com.fiap.fast_food_tc.domain.entity.Payment;
import com.fiap.fast_food_tc.domain.entity.Product;
import com.fiap.fast_food_tc.domain.enums.PaymentStatus;
import com.fiap.fast_food_tc.domain.enums.StatusOrder;
import com.fiap.fast_food_tc.infrastructure.web.mapper.PaymentMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class CheckoutUseCaseImpl implements CheckoutUseCase {

    private final CheckoutGateway checkoutGateway;
    private final OrdersUseCase ordersUseCase;
    private final OrderProductUseCase orderProductUseCase;
    private final ProductUseCase productUseCase;
    private final PaymentGateway paymentGateway;
    private final PaymentMapper paymentMapper;
    @Autowired
    public CheckoutUseCaseImpl(CheckoutGateway checkoutGateway,
                               OrdersUseCase ordersUseCase,
                               OrderProductUseCase orderProductUseCase,
                               ProductUseCase productUseCase, PaymentGateway paymentGateway, PaymentMapper paymentMapper) {
        this.checkoutGateway = checkoutGateway;
        this.ordersUseCase = ordersUseCase;
        this.orderProductUseCase = orderProductUseCase;
        this.productUseCase = productUseCase;
        this.paymentGateway = paymentGateway;
        this.paymentMapper = paymentMapper;
    }


    @Override
    @Transactional
    public Checkout checkoutAndCreateOrder(CheckoutOrder request) {
        Orders order = Orders.builder()
                .orderDatetime(java.time.LocalDateTime.now())
                .statusOrder(StatusOrder.PAYMENT_PENDING)
                .totalAmount(BigDecimal.ZERO)
                .customerId(request.getCustomerId())
                .orderCode(ordersUseCase.getNextOrderCode())
                .build();

        order = ordersUseCase.create(order);

        BigDecimal total = BigDecimal.ZERO;

        for (CheckoutOrder.Item item : request.getItems()) {
            Product product = productUseCase.findById(item.getProductId());
            BigDecimal itemTotal = product.getProductValue()
                    .multiply(BigDecimal.valueOf(item.getQuantity()));
            total = total.add(itemTotal);

            OrderProduct op = OrderProduct.builder()
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

    private Checkout buildCheckout(BigDecimal total, Orders order, String paymentLink, CheckoutOrder request) {
        return Checkout.builder()
                .totalAmount(total)
                .orderId(order.getOrderId())
                .orderCode(order.getOrderCode())
                .orderRequest(request)
                .paymentLink(paymentLink)
                .statusOrder(order.getStatusOrder())
                .build();
    }


    @Override
    public Orders handleWebhook(String paymentId) {

        MPPaymentResponse MPResponse = checkoutGateway.findMercadoPagoPaymentResponse(paymentId);
        Payment payment = paymentMapper.toEntity(paymentGateway.findByMercadoPagoId(MPResponse.getExternal_reference()));
        Orders order = ordersUseCase.getById(payment.getOrderId());
        if ("approved".equalsIgnoreCase(MPResponse.getStatus())) {
            payment.setPaymentStatus(PaymentStatus.APPROVED);
            paymentGateway.save(paymentMapper.toModel(payment));
            if (order == null) {
                return null;
            }
            ordersUseCase.updateStatusOrder(order.getOrderId(), StatusOrder.IN_PREPARATION);
            List<OrderProduct> orderProductList = orderProductUseCase.getByOrderId(order.getOrderId());

            orderProductList.forEach(orderProduct -> productUseCase.subtractQuantity(orderProduct.getProductId(), orderProduct.getProductQuantity()));

            return order;
        }
        if ("rejected".equalsIgnoreCase(MPResponse.getStatus())) {
            payment.setPaymentStatus(PaymentStatus.REJECTED);
            paymentGateway.save(paymentMapper.toModel(payment));
            if (order != null) {
                ordersUseCase.updateStatusOrder(order.getOrderId(), StatusOrder.CANCELED);
            }
            return order;
        }
        return order;

    }

}
