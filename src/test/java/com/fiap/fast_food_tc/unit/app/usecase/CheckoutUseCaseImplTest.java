package com.fiap.fast_food_tc.unit.app.usecase;

import com.fiap.fast_food_tc.application.dto.checkout.out.MPPaymentResponse;
import com.fiap.fast_food_tc.application.gateway.CheckoutGateway;
import com.fiap.fast_food_tc.application.gateway.PaymentGateway;
import com.fiap.fast_food_tc.application.usecase.OrderProductUseCase;
import com.fiap.fast_food_tc.application.usecase.OrdersUseCase;
import com.fiap.fast_food_tc.application.usecase.ProductUseCase;
import com.fiap.fast_food_tc.application.usecase.impl.CheckoutUseCaseImpl;
import com.fiap.fast_food_tc.domain.entity.CheckoutOrder;
import com.fiap.fast_food_tc.domain.entity.OrderProduct;
import com.fiap.fast_food_tc.domain.entity.Orders;
import com.fiap.fast_food_tc.domain.entity.Payment;
import com.fiap.fast_food_tc.domain.entity.Product;
import com.fiap.fast_food_tc.domain.enums.PaymentStatus;
import com.fiap.fast_food_tc.domain.enums.StatusOrder;
import com.fiap.fast_food_tc.infrastructure.persistence.entity.OrdersPersistenceEntity;
import com.fiap.fast_food_tc.infrastructure.persistence.entity.PaymentPersistenceEntity;
import com.fiap.fast_food_tc.infrastructure.web.mapper.PaymentMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CheckoutUseCaseImplTest {

    @Mock
     private ProductUseCase productUseCase;
    @Mock
    private OrderProductUseCase orderProductUseCase;
    @Mock
    private CheckoutGateway checkoutGateway;
    @Mock
    private OrdersUseCase ordersUseCase;
    @Mock
    private PaymentGateway paymentGateway;
    @Mock
    private PaymentMapper paymentMapper;
    @InjectMocks
    private CheckoutUseCaseImpl useCase;

    private MPPaymentResponse mpPaymentResponse;
    private PaymentPersistenceEntity paymentPersistenceEntity;
    private Payment payment;
    private Orders initialOrder;
    private Orders updatedOrder;

    @BeforeEach
    void setUp() {

        mpPaymentResponse = new MPPaymentResponse();
        mpPaymentResponse.setId(1L);
        mpPaymentResponse.setPayment_type_id("PIX");
        mpPaymentResponse.setPayment_method_id("PIX");
        mpPaymentResponse.setTransaction_amount(BigDecimal.ONE);
        mpPaymentResponse.setExternal_reference("external-ref-123");

        paymentPersistenceEntity = new PaymentPersistenceEntity();
        paymentPersistenceEntity.setPaymentId(1);
        paymentPersistenceEntity.setMercadoPagoId("external-ref-123");

        OrdersPersistenceEntity ordersPersistenceEntity = new OrdersPersistenceEntity();
        ordersPersistenceEntity.setOrderId(1);
        paymentPersistenceEntity.setOrdersPersistenceEntity(ordersPersistenceEntity);

        payment = new Payment();
        payment.setPaymentId(1);
        payment.setOrderId(1);
        payment.setMercadoPagoId("external-ref-123");

        initialOrder = new Orders();
        initialOrder.setOrderId(1);
        initialOrder.setStatusOrder(StatusOrder.PAYMENT_PENDING);

        updatedOrder = new Orders();
        updatedOrder.setOrderId(1);
    }

    @Test
    void checkoutWebhookApproved() {
        mpPaymentResponse.setStatus("approved");
        updatedOrder.setStatusOrder(StatusOrder.RECEIVED);

        payment.setPaymentStatus(PaymentStatus.APPROVED);

        PaymentPersistenceEntity expectedSavedEntity = new PaymentPersistenceEntity();
        expectedSavedEntity.setPaymentStatus(PaymentStatus.APPROVED);

        when(checkoutGateway.findMercadoPagoPaymentResponse("payment-123")).thenReturn(mpPaymentResponse);
        when(paymentGateway.findByMercadoPagoId("external-ref-123")).thenReturn(paymentPersistenceEntity);
        when(paymentMapper.toEntity(paymentPersistenceEntity)).thenReturn(payment);
        when(paymentMapper.toModel(payment)).thenReturn(expectedSavedEntity);
        when(ordersUseCase.getById(1)).thenReturn(initialOrder);
        when(ordersUseCase.updateStatusOrder(1, StatusOrder.RECEIVED)).thenReturn(updatedOrder);

        Orders actualOrder = useCase.handleWebhook("payment-123");

        assertEquals(updatedOrder, actualOrder);
        assertEquals(StatusOrder.RECEIVED, actualOrder.getStatusOrder());

        verify(paymentGateway).save(expectedSavedEntity);
        verify(ordersUseCase).updateStatusOrder(1, StatusOrder.RECEIVED);
    }

    @Test
    void checkoutWebhookRejected() {
        mpPaymentResponse.setStatus("rejected");
        updatedOrder.setStatusOrder(StatusOrder.CANCELED);

        payment.setPaymentStatus(PaymentStatus.REJECTED);

        PaymentPersistenceEntity expectedSavedEntity = new PaymentPersistenceEntity();
        expectedSavedEntity.setPaymentStatus(PaymentStatus.REJECTED);

        when(checkoutGateway.findMercadoPagoPaymentResponse("payment-123")).thenReturn(mpPaymentResponse);
        when(paymentGateway.findByMercadoPagoId("external-ref-123")).thenReturn(paymentPersistenceEntity);
        when(paymentMapper.toEntity(paymentPersistenceEntity)).thenReturn(payment);
        when(paymentMapper.toModel(payment)).thenReturn(expectedSavedEntity);
        when(ordersUseCase.getById(1)).thenReturn(initialOrder);
        when(ordersUseCase.updateStatusOrder(1, StatusOrder.CANCELED)).thenReturn(updatedOrder);

        Orders actualOrder = useCase.handleWebhook("payment-123");

        assertEquals(updatedOrder, actualOrder);
        assertEquals(StatusOrder.CANCELED, actualOrder.getStatusOrder());

        verify(paymentGateway).save(expectedSavedEntity);
        verify(ordersUseCase).updateStatusOrder(1, StatusOrder.CANCELED);
    }

    @Test
    void checkoutAndCreateOrderSuccess() {
        CheckoutOrder.Item item = CheckoutOrder.Item.builder()
                .productId(1)
                .quantity(1)
                .build();
        CheckoutOrder request = CheckoutOrder.builder()
                .customerId(1)
                .items(java.util.List.of(item))
                .build();

        when(ordersUseCase.getNextOrderCode()).thenReturn((short)1);
        when(ordersUseCase.create(Mockito.any(Orders.class))).thenReturn(Orders.builder().orderId(1).build());
        when(productUseCase.findById(Mockito.anyInt())).thenReturn(Product.builder().productId(1).productValue(java.math.BigDecimal.ONE).build());
        when(orderProductUseCase.create(Mockito.any(OrderProduct.class))).thenReturn(OrderProduct.builder().build());
        when(ordersUseCase.update(Mockito.anyInt(), Mockito.any(Orders.class))).thenReturn(Orders.builder().orderId(1).build());
        when(checkoutGateway.getPaymentLink(1)).thenReturn("link");

        var result = useCase.checkoutAndCreateOrder(request);

        assertEquals("link", result.getPaymentLink());
    }
}
