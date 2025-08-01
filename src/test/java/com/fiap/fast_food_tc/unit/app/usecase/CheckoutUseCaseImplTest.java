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
import com.fiap.fast_food_tc.domain.enums.PaymentMethod;
import com.fiap.fast_food_tc.domain.enums.PaymentStatus;
import com.fiap.fast_food_tc.domain.enums.StatusOrder;
import com.fiap.fast_food_tc.infrastructure.persistence.entity.CustomerPersistenceEntity;
import com.fiap.fast_food_tc.infrastructure.persistence.entity.OrdersPersistenceEntity;
import com.fiap.fast_food_tc.infrastructure.persistence.entity.PaymentPersistenceEntity;
import com.fiap.fast_food_tc.infrastructure.web.mapper.PaymentMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;

@ExtendWith(MockitoExtension.class)
class CheckoutUseCaseImplTest {

    @Mock
    private CheckoutGateway checkoutGateway;
    @Mock
    private OrdersUseCase ordersUseCase;
    @Mock
    private OrderProductUseCase orderProductUseCase;
    @Mock
    private ProductUseCase productUseCase;
    @Mock
    private PaymentGateway paymentGateway;
    @Mock
    private PaymentMapper paymentMapper;
    @InjectMocks
    private CheckoutUseCaseImpl useCase;

    @ParameterizedTest
    @ValueSource(strings = {"APPROVED", "REJECTED"})
    void handleWebhook(String input) {

        MPPaymentResponse.Payer payer = new MPPaymentResponse.Payer().builder()
                .email("email")
                .first_name("nome")
                .last_name("sobrenome")
                .build();

        MPPaymentResponse.Order order = new MPPaymentResponse.Order().builder()
                .id("id")
                .build();

        MPPaymentResponse.TransactionDetails transactionDetails =
                new MPPaymentResponse.TransactionDetails().builder()
                        .total_paid_amount(BigDecimal.ONE)
                        .net_received_amount(BigDecimal.ONE)
                        .build();

        MPPaymentResponse mpPaymentResponse = new MPPaymentResponse().builder()
                .id(1L)
                .status(input)
                .payment_type_id("PIX")
                .payment_method_id("PIX")
                .transaction_amount(BigDecimal.ONE)
                .external_reference("external")
                .payer(payer)
                .order(order)
                .transaction_details(transactionDetails)
                .build();

        OrdersPersistenceEntity ordersPersistenceEntity = new OrdersPersistenceEntity().builder()
                .orderId(1)
                .orderDatetime(LocalDateTime.now())
                .statusOrder(StatusOrder.FINISHED)
                .orderCode(Short.MAX_VALUE)
                .totalAmount(BigDecimal.ONE)
                .customerPersistenceEntity(new CustomerPersistenceEntity())
                .orderProductPersistenceEntities(new ArrayList<>())
                .paymentPersistenceEntity(new PaymentPersistenceEntity())
                .build();

        PaymentPersistenceEntity paymentPersistenceEntity = new PaymentPersistenceEntity().builder()
                .paymentId(1)
                .paymentMethod(PaymentMethod.PIX)
                .paymentStatus(PaymentStatus.APPROVED)
                .createdAt(LocalDateTime.now())
                .paymentValue(BigDecimal.ONE)
                .mercadoPagoId("1")
                .customerId(1)
                .ordersPersistenceEntity(ordersPersistenceEntity)
                .build();

        Payment payment = new Payment().builder()
                .paymentId(1)
                .paymentMethod(PaymentMethod.PIX)
                .paymentStatus(PaymentStatus.APPROVED)
                .createdAt(LocalDateTime.now())
                .paymentValue(BigDecimal.ONE)
                .mercadoPagoId("1")
                .customerId(1)
                .orderId(1)
                .build();

        Orders orders = new Orders().builder()
                .orderId(1)
                .orderDatetime(LocalDateTime.now())
                .statusOrder(StatusOrder.PAYMENT_PENDING)
                .orderCode(Short.MAX_VALUE)
                .totalAmount(BigDecimal.ONE)
                .customerId(1)
                .build();

        Mockito.when(checkoutGateway.findMercadoPagoPaymentResponse(anyString())).thenReturn(mpPaymentResponse);
        Mockito.when(paymentGateway.findByMercadoPagoId(anyString())).thenReturn(paymentPersistenceEntity);
        Mockito.when(paymentMapper.toEntity(any(PaymentPersistenceEntity.class))).thenReturn(payment);
        Mockito.when(ordersUseCase.getById(anyInt())).thenReturn(orders);
        Mockito.when(paymentMapper.toModel(any(Payment.class))).thenReturn(paymentPersistenceEntity);
        Mockito.when(ordersUseCase.updateStatusOrder(anyInt(),any(StatusOrder.class))).thenReturn(orders);

        Orders actual = useCase.handleWebhook("123");

        Mockito.verify(checkoutGateway,Mockito.times(1)).findMercadoPagoPaymentResponse(anyString());
        Mockito.verify(paymentGateway,Mockito.times(1)).findByMercadoPagoId(anyString());
        Mockito.verify(paymentMapper,Mockito.times(1)).toEntity(any(PaymentPersistenceEntity.class));
        Mockito.verify(ordersUseCase,Mockito.times(1)).getById(anyInt());
        Mockito.verify(paymentMapper,Mockito.times(1)).toModel(any(Payment.class));
        Mockito.verify(ordersUseCase,Mockito.times(1)).updateStatusOrder(anyInt(), any(StatusOrder.class));
        assertEquals(orders, actual);
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

        Mockito.when(ordersUseCase.getNextOrderCode()).thenReturn((short)1);
        Mockito.when(ordersUseCase.create(Mockito.any(Orders.class))).thenReturn(Orders.builder().orderId(1).build());
        Mockito.when(productUseCase.findById(Mockito.anyInt())).thenReturn(Product.builder().productId(1).productValue(java.math.BigDecimal.ONE).build());
        Mockito.when(orderProductUseCase.create(Mockito.any(OrderProduct.class))).thenReturn(OrderProduct.builder().build());
        Mockito.when(ordersUseCase.update(Mockito.anyInt(), Mockito.any(Orders.class))).thenReturn(Orders.builder().orderId(1).build());
        Mockito.when(checkoutGateway.getPaymentLink(1)).thenReturn("link");

        var result = useCase.checkoutAndCreateOrder(request);

        assertEquals("link", result.getPaymentLink());
    }
}
