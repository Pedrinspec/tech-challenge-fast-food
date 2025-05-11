package com.fiap.fast_food_tc.adapter.db.model;

import com.fiap.fast_food_tc.adapter.db.model.ids.PaymentPk;
import com.fiap.fast_food_tc.cross.enums.PaymentMethod;
import com.fiap.fast_food_tc.cross.enums.PaymentStatus;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "payment")
public class Payment {

    @EmbeddedId
    private PaymentPk id;

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
