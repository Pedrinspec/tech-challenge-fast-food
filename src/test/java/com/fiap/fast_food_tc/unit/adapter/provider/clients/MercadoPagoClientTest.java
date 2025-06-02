package com.fiap.fast_food_tc.unit.adapter.provider.clients;

import com.fiap.fast_food_tc.adapter.dto.checkout.PreferenceRequest;
import com.fiap.fast_food_tc.adapter.dto.checkout.PreferenceResponse;
import com.fiap.fast_food_tc.adapter.provider.clients.MercadoPagoClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpHeaders;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MercadoPagoClientTest {

    @Mock
    private WebClient webClient;
    @Mock
    private WebClient.RequestBodyUriSpec uriSpec;
    @Mock
    private WebClient.RequestHeadersSpec<?> headersSpec;
    @Mock
    private WebClient.ResponseSpec responseSpec;

    private MercadoPagoClient client;

    @BeforeEach
    void setUp() {
        client = new MercadoPagoClient(webClient, "token");
    }

    @Test
    void createPreferenceSuccess() {
        PreferenceRequest request = new PreferenceRequest();
        PreferenceResponse response = PreferenceResponse.builder().id("1").initPoint("url").build();

        when(webClient.post()).thenReturn(uriSpec);
        when(uriSpec.uri("/checkout/preferences")).thenReturn(uriSpec);
        when(uriSpec.header(HttpHeaders.AUTHORIZATION, "Bearer token")).thenReturn(uriSpec);
        when(uriSpec.bodyValue(request)).thenReturn(headersSpec);
        when(headersSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.bodyToMono(PreferenceResponse.class)).thenReturn(Mono.just(response));

        var result = client.createPreference(request);

        assertEquals(response, result);
    }
}
