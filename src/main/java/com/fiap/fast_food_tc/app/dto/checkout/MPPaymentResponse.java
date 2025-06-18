package com.fiap.fast_food_tc.app.dto.checkout;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MPPaymentResponse {

    public Long id;
    public String status;
    public String payment_type_id;
    public String payment_method_id;
    public BigDecimal transaction_amount;
    public String external_reference;

    public Payer payer;
    public Order order;
    public TransactionDetails transaction_details;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Payer {
        public String email;
        public String first_name;
        public String last_name;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Order {
        public String id;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class TransactionDetails {
        public BigDecimal total_paid_amount;
        public BigDecimal net_received_amount;
    }

}
