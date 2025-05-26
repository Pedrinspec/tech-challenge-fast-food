package com.fiap.fast_food_tc.unit.adapter.provider.clients;

import com.fiap.fast_food_tc.adapter.dto.checkout.PreferenceRequest;
import com.fiap.fast_food_tc.adapter.dto.checkout.PreferenceResponse;
import com.fiap.fast_food_tc.adapter.provider.clients.MercadoPagoClient;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MercadoPagoClientTest {

    private MockWebServer server;
    private MercadoPagoClient client;

    @BeforeEach
    void setUp() throws IOException {
        server = new MockWebServer();
        server.start();
        WebClient webClient = WebClient.builder()
                .baseUrl(server.url("/").toString())
                .build();
        client = new MercadoPagoClient(webClient, "TOKEN");
    }

    @AfterEach
    void tearDown() throws IOException {
        server.shutdown();
    }

    @Test
    void shouldCallMercadoPagoApi() throws InterruptedException {
        server.enqueue(new MockResponse()
                .setHeader("Content-Type", "application/json")
                .setBody("{\"id\":\"123\",\"init_point\":\"http://example.com\"}"));

        PreferenceResponse response = client.createPreference(new PreferenceRequest());

        RecordedRequest recorded = server.takeRequest();
        assertEquals("/checkout/preferences", recorded.getPath());
        assertEquals("Bearer TOKEN", recorded.getHeader(HttpHeaders.AUTHORIZATION));
        assertEquals("123", response.getId());
        assertEquals("http://example.com", response.getInitPoint());
    }
}
