package com.fiap.fast_food_tc.infra.provider;

import com.fiap.fast_food_tc.app.dto.checkout.MPPaymentResponse;
import com.fiap.fast_food_tc.infra.db.model.Orders;
import com.fiap.fast_food_tc.infra.db.model.Payment;
import com.fiap.fast_food_tc.infra.db.model.Product;
import com.fiap.fast_food_tc.app.dto.checkout.PreferenceRequest;
import com.fiap.fast_food_tc.app.dto.checkout.PreferenceResponse;
import com.fiap.fast_food_tc.infra.provider.clients.MercadoPagoClient;
import com.fiap.fast_food_tc.cross.enums.PaymentMethod;
import com.fiap.fast_food_tc.cross.enums.PaymentStatus;
import com.fiap.fast_food_tc.domain.gateway.CheckoutGateway;
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
            Payment payment = paymentDataProvider.findByMercadoPagoId(paymentId);
            payment.setPaymentStatus(PaymentStatus.APPROVED);
            paymentDataProvider.save(payment);
        }
    }

    @Transactional
    @Override
    public String getPaymentLink(Integer orderId) {

        var order = ordersDataProvider.getById(orderId);
        if (order.getOrderProducts() == null) {
            order.setOrderProducts(orderProductDataProvider.findByOrderId(orderId));
        }
        PreferenceRequest request = getPreferenceRequest(order);
        PreferenceResponse response = mercadoPagoClient.createPreference(request);

        Payment payment = Payment.builder()
                .customerId(order.getCustomer().getCustomerId())
                .paymentValue(order.getTotalAmount())
                .paymentStatus(PaymentStatus.PENDING)
                .createdAt(LocalDateTime.now())
                .paymentMethod(PaymentMethod.MERCADO_PAGO)
                .mercadoPagoId(response.getId())
                .build();
        paymentDataProvider.save(payment);

        return response.getInitPoint();
    }

    private PreferenceRequest getPreferenceRequest(Orders order) {
        List<PreferenceRequest.Item> items = order.getOrderProducts().stream().map(op -> {
            Product product = op.getProduct();
            PreferenceRequest.Item item = new PreferenceRequest.Item();
            item.setTitle(product.getName());
            item.setCurrencyId("BRL");
            item.setQuantity(op.getProductQuantity());
            item.setUnitPrice(product.getProductValue());
            return item;
        }).toList();

        return buildRequest(order, items);
    }

    private static PreferenceRequest buildRequest(Orders order, List<PreferenceRequest.Item> items) {
        PreferenceRequest.BackUrls urls = new PreferenceRequest.BackUrls();
        urls.setSuccess("https://seudominio.com/pagamento/aprovado");
        urls.setFailure("https://seudominio.com/pagamento/falha");
        urls.setPending("https://seudominio.com/pagamento/pendente");

        PreferenceRequest request = new PreferenceRequest();
        request.setItems(items);
        request.setExternalReference("PEDIDO_" + order.getOrderId());
        request.setBackUrls(urls);
        request.setAutoReturn("approved");
        return request;
    }
}
