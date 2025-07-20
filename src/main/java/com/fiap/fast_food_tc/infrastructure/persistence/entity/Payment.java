package com.fiap.fast_food_tc.infrastructure.persistence.entity;

import com.fiap.fast_food_tc.domain.enums.PaymentMethod;
import com.fiap.fast_food_tc.domain.enums.PaymentStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "payment")
public class Payment {

    @Id
    @Column(name = "payment_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer paymentId;

    @Column(name = "payment_method", nullable = false)
    private PaymentMethod paymentMethod;

    @Column(name = "payment_status", nullable = false)
    private PaymentStatus paymentStatus;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "payment_value", nullable = false, precision = 10, scale = 2)
    private BigDecimal paymentValue;

    @Column(name = "mercado_pago_id")
    private String mercadoPagoId;

    @Column(name = "customer_id")
    private Integer customerId;

    @OneToOne
    @JoinColumn(name = "order_id", referencedColumnName = "order_id", unique = true)
    private Orders orders;

}
