package com.fiap.fast_food_tc.application.dto.checkout.in;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CheckoutWebhookRequest {

    private String action;

    @JsonProperty("api_version")
    private String apiVersion;

    @NotNull(message = "Object 'data' cannot be null")
    @Valid
    private WebhookData data;

    @JsonProperty("date_created")
    private ZonedDateTime dateCreated;

    private long id;

    @JsonProperty("live_mode")
    private boolean liveMode;

    private String type;

    @JsonProperty("user_id")
    private String userId;

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class WebhookData {

        @NotBlank(message = "Field 'data.id' cannot be null.")
        private String id;

    }
}
