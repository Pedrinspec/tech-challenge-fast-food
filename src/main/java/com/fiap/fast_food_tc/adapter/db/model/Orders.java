package com.fiap.fast_food_tc.adapter.db.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@Entity
@Table(name = "orders")
@AllArgsConstructor
@NoArgsConstructor
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id", nullable = false, unique = true)
    private Integer orderId;

    @Column(name = "order_datetime")
    private LocalDateTime orderDatetime;

    @Column(name = "status_order")
    private Integer statusOrder;

    @Column(name = "order_code", precision = 3)
    private Short orderCode;

    @Column(name = "total_amount", precision = 10, scale = 2, nullable = false)
    private BigDecimal totalAmount;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

}
