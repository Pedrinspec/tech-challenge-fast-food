package com.fiap.fast_food_tc.unit.infra.provider;

import com.fiap.fast_food_tc.domain.enums.StatusOrder;
import com.fiap.fast_food_tc.infrastructure.persistence.entity.OrderProductPersistenceEntity;
import com.fiap.fast_food_tc.infrastructure.persistence.entity.OrdersPersistenceEntity;
import com.fiap.fast_food_tc.infrastructure.persistence.entity.PaymentPersistenceEntity;
import com.fiap.fast_food_tc.infrastructure.persistence.entity.ProductPersistenceEntity;
import com.fiap.fast_food_tc.infrastructure.persistence.entity.ids.OrderProductPk;
import com.fiap.fast_food_tc.application.dto.checkout.PreferenceResponse;
import com.fiap.fast_food_tc.infrastructure.persistence.dataprovider.CheckoutDataProvider;
import com.fiap.fast_food_tc.infrastructure.persistence.dataprovider.OrdersDataProvider;
import com.fiap.fast_food_tc.infrastructure.persistence.dataprovider.PaymentDataProvider;
import com.fiap.fast_food_tc.infrastructure.client.MercadoPagoClient;
import com.fiap.fast_food_tc.domain.enums.PaymentMethod;
import com.fiap.fast_food_tc.domain.enums.PaymentStatus;
import fixture.CustomerFixture;
import fixture.ProductFixture;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CheckoutDataProviderTest {

    @Mock
    private MercadoPagoClient mercadoPagoClient;
    @Mock
    private PaymentDataProvider paymentDataProvider;
    @Mock
    private OrdersDataProvider ordersDataProvider;

    @InjectMocks
    private CheckoutDataProvider provider;

    @Test
    void getPaymentLinkSuccess() {
        OrdersPersistenceEntity order = createOrder();
        PreferenceResponse response = PreferenceResponse.builder()
                .externalReference("123")
                .sandboxInitPoint("http://pay")
                .build();

        when(ordersDataProvider.getById(1)).thenReturn(order);
        when(mercadoPagoClient.createPreference(any())).thenReturn(response);
        when(paymentDataProvider.save(any())).thenReturn(null);

        String link = provider.getPaymentLink(1);

        assertEquals("http://pay", link);
        verify(mercadoPagoClient).createPreference(any());

        ArgumentCaptor<PaymentPersistenceEntity> captor = ArgumentCaptor.forClass(PaymentPersistenceEntity.class);
        verify(paymentDataProvider).save(captor.capture());
        PaymentPersistenceEntity saved = captor.getValue();
        assertEquals(order.getCustomerPersistenceEntity().getCustomerId(), saved.getCustomerId());
        assertEquals(order.getTotalAmount(), saved.getPaymentValue());
        assertEquals(PaymentStatus.PENDING, saved.getPaymentStatus());
        assertEquals(PaymentMethod.MERCADO_PAGO, saved.getPaymentMethod());
        assertEquals("123", saved.getMercadoPagoId());
        assertNotNull(saved.getCreatedAt());
    }

    private OrdersPersistenceEntity createOrder() {
        OrdersPersistenceEntity order = OrdersPersistenceEntity.builder()
                .orderId(1)
                .totalAmount(BigDecimal.TEN)
                .customerPersistenceEntity(CustomerFixture.createCustomerModel())
                .orderDatetime(LocalDateTime.now())
                .statusOrder(StatusOrder.READY_FOR_PICKUP)
                .build();

        ProductPersistenceEntity productPersistenceEntity = ProductFixture.createProduct();
        OrderProductPersistenceEntity op = new OrderProductPersistenceEntity();
        op.setOrdersPersistenceEntity(order);
        op.setProductPersistenceEntity(productPersistenceEntity);
        op.setProductQuantity(2);
        op.setProductTotalAmount(BigDecimal.TEN);
        op.setId(new OrderProductPk(order.getOrderId(), productPersistenceEntity.getProductId()));
        order.setOrderProductPersistenceEntities(List.of(op));
        return order;
    }
}
