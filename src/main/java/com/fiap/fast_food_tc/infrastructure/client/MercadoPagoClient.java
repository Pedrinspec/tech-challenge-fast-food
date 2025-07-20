package com.fiap.fast_food_tc.infrastructure.client;

import com.fiap.fast_food_tc.application.dto.checkout.MPPaymentResponse;
import com.fiap.fast_food_tc.application.dto.checkout.PreferenceRequest;
import com.fiap.fast_food_tc.application.dto.checkout.PreferenceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class MercadoPagoClient {

    private final WebClient mercadoPagoWebClient;
    private final String mercadoPagoToken;

    @Autowired
    public MercadoPagoClient(@Qualifier("mercadoPagoWebClient")WebClient mercadoPagoWebClient,
                             @Value("${mercadopago.access.token}")String mercadoPagoToken) {
        this.mercadoPagoWebClient = mercadoPagoWebClient;
        this.mercadoPagoToken = mercadoPagoToken;
    }

    public PreferenceResponse createPreference(PreferenceRequest request) {
        return mercadoPagoWebClient.post()
                .uri("/checkout/preferences")
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + mercadoPagoToken)
                .bodyValue(request)
                .retrieve()
                .bodyToMono(PreferenceResponse.class)
                .block();
    }

    public MPPaymentResponse getPayment(String id) {
        return mercadoPagoWebClient.get()
                .uri("/v1/payments/{id}", id)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + mercadoPagoToken)
                .retrieve()
                .bodyToMono(MPPaymentResponse.class)
                .block();
    }
}
