package com.fiap.fast_food_tc.adapter.provider;

import com.fiap.fast_food_tc.adapter.db.model.Orders;
import com.fiap.fast_food_tc.adapter.db.model.Payment;
import com.fiap.fast_food_tc.adapter.db.model.Product;
import com.fiap.fast_food_tc.adapter.dto.checkout.PreferenceRequest;
import com.fiap.fast_food_tc.adapter.dto.checkout.PreferenceResponse;
import com.fiap.fast_food_tc.adapter.provider.clients.MercadoPagoClient;
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

    @Autowired
    public CheckoutDataProvider(MercadoPagoClient mercadoPagoClient, PaymentDataProvider paymentDataProvider) {
        this.mercadoPagoClient = mercadoPagoClient;
        this.paymentDataProvider = paymentDataProvider;
    }


    @Transactional
    @Override
    public String getPaymentLink(Orders order) {


        PreferenceRequest request = getPreferenceRequest(order);
        PreferenceResponse response = mercadoPagoClient.createPreference(request);

        Payment payment = new Payment();
        payment.setOrders(order);
        payment.setPaymentStatus(PaymentStatus.PENDING);
        payment.setCreatedAt(LocalDateTime.now());
        payment.setPaymentMethod(PaymentMethod.MERCADO_PAGO);
        payment.setMercadoPagoId(response.getId());
        paymentDataProvider.save(payment);

        //TODO implementar metodo para salvar o status do pedido como em preparação apos o pagamento

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
        urls.setSuccess("https://seudominio.com/pagamento/sucesso");
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
