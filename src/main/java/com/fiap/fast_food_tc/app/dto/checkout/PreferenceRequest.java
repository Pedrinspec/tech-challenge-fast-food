package com.fiap.fast_food_tc.app.dto.checkout;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PreferenceRequest {
    private List<Item> items;

    @JsonProperty("external_reference")
    private String externalReference;

    @JsonProperty("back_urls")
    private BackUrls backUrls;

    @JsonProperty("auto_return")
    private String autoReturn;


    @Builder
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Item {
        private String title;
        private int quantity;

        @JsonProperty("currency_id")
        private String currencyId;

        @JsonProperty("unit_price")
        private BigDecimal unitPrice;
    }

    @Builder
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class BackUrls {
        private String success;
        private String failure;
        private String pending;
    }

}


