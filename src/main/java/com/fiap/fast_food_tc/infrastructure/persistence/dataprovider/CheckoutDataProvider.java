package com.fiap.fast_food_tc.infrastructure.persistence.dataprovider;

import com.fiap.fast_food_tc.application.dto.checkout.out.MPPaymentResponse;
import com.fiap.fast_food_tc.domain.enums.StatusOrder;
import com.fiap.fast_food_tc.infrastructure.persistence.entity.OrdersPersistenceEntity;
import com.fiap.fast_food_tc.infrastructure.persistence.entity.PaymentPersistenceEntity;
import com.fiap.fast_food_tc.infrastructure.persistence.entity.ProductPersistenceEntity;
import com.fiap.fast_food_tc.application.dto.checkout.in.PreferenceRequest;
import com.fiap.fast_food_tc.application.dto.checkout.out.PreferenceResponse;
import com.fiap.fast_food_tc.infrastructure.client.MercadoPagoClient;
import com.fiap.fast_food_tc.domain.enums.PaymentMethod;
import com.fiap.fast_food_tc.domain.enums.PaymentStatus;
import com.fiap.fast_food_tc.application.gateway.CheckoutGateway;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class CheckoutDataProvider implements CheckoutGateway {

    private final MercadoPagoClient mercadoPagoClient;
    private final PaymentDataProvider paymentDataProvider;
    private final OrdersDataProvider ordersDataProvider;
    private final OrderProductDataProvider orderProductDataProvider;

    @Autowired
    public CheckoutDataProvider(MercadoPagoClient mercadoPagoClient, PaymentDataProvider paymentDataProvider, OrdersDataProvider ordersDataProvider, OrderProductDataProvider orderProductDataProvider) {
        this.mercadoPagoClient = mercadoPagoClient;
        this.paymentDataProvider = paymentDataProvider;
        this.ordersDataProvider = ordersDataProvider;
        this.orderProductDataProvider = orderProductDataProvider;
    }

    @Override
    public void verifyApprovedPayment(String paymentId){
        MPPaymentResponse response = mercadoPagoClient.getPayment(paymentId);
        if (response != null && "approved".equalsIgnoreCase(response.getStatus())) {
            PaymentPersistenceEntity paymentPersistenceEntity = paymentDataProvider.findByMercadoPagoId(response.getExternal_reference());
            paymentPersistenceEntity.setPaymentStatus(PaymentStatus.APPROVED);
            paymentDataProvider.save(paymentPersistenceEntity);

            OrdersPersistenceEntity order = paymentPersistenceEntity.getOrdersPersistenceEntity();
            if (order != null) {
                order.setStatusOrder(StatusOrder.IN_PREPARATION);
                ordersDataProvider.update(order);
            }
        }
    }

    @Transactional
    @Override
    public String getPaymentLink(Integer orderId) {

        var order = ordersDataProvider.getById(orderId);
        if (order.getOrderProductPersistenceEntities() == null) {
            order.setOrderProductPersistenceEntities(orderProductDataProvider.findByOrderId(orderId));
        }
        PreferenceRequest request = getPreferenceRequest(order);
        PreferenceResponse response = mercadoPagoClient.createPreference(request);

        PaymentPersistenceEntity paymentPersistenceEntity = PaymentPersistenceEntity.builder()
                .customerId(order.getCustomerPersistenceEntity().getCustomerId())
                .paymentValue(order.getTotalAmount())
                .paymentStatus(PaymentStatus.PENDING)
                .createdAt(LocalDateTime.now())
                .paymentMethod(PaymentMethod.MERCADO_PAGO)
                .mercadoPagoId(response.getExternalReference())
                .ordersPersistenceEntity(order)
                .build();
        paymentDataProvider.save(paymentPersistenceEntity);

        return response.getSandboxInitPoint();
    }

    private PreferenceRequest getPreferenceRequest(OrdersPersistenceEntity order) {
        List<PreferenceRequest.Item> items = order.getOrderProductPersistenceEntities().stream().map(op -> {
            ProductPersistenceEntity productPersistenceEntity = op.getProductPersistenceEntity();
            PreferenceRequest.Item item = new PreferenceRequest.Item();
            item.setTitle(productPersistenceEntity.getName());
            item.setCurrencyId("BRL");
            item.setQuantity(op.getProductQuantity());
            item.setUnitPrice(productPersistenceEntity.getProductValue());
            return item;
        }).toList();

        return buildRequest(order, items);
    }

    private static PreferenceRequest buildRequest(OrdersPersistenceEntity order, List<PreferenceRequest.Item> items) {
        PreferenceRequest.BackUrls urls = new PreferenceRequest.BackUrls();
        urls.setSuccess("https://huge-musician-87.webhook.cool/pagamento/aprovado");
        urls.setFailure("https://huge-musician-87.webhook.cool/pagamento/falha");
        urls.setPending("https://huge-musician-87.webhook.cool/pagamento/pendente");

        PreferenceRequest request = new PreferenceRequest();
        request.setItems(items);
        request.setExternalReference("PEDIDO_" + order.getOrderId());
        request.setBackUrls(urls);
        request.setAutoReturn("approved");
        request.setNotificationUrl("https://huge-musician-87.webhook.cool/pagamento");
        return request;
    }
}
