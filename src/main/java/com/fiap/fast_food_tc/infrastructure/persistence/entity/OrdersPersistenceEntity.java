package com.fiap.fast_food_tc.infrastructure.persistence.entity;

import com.fiap.fast_food_tc.domain.enums.StatusOrder;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter
@Setter
@Entity
@Table(name = "orders")
@AllArgsConstructor
@NoArgsConstructor
public class OrdersPersistenceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id", nullable = false, unique = true)
    private Integer orderId;

    @Column(name = "order_datetime")
    private LocalDateTime orderDatetime;

    @Column(name = "status_order")
    private StatusOrder statusOrder;

    @Column(name = "order_code", precision = 3)
    private Short orderCode;

    @Column(name = "total_amount", precision = 10, scale = 2, nullable = false)
    private BigDecimal totalAmount;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private CustomerPersistenceEntity customerPersistenceEntity;

    @OneToMany(mappedBy = "ordersPersistenceEntity")
    private List<OrderProductPersistenceEntity> orderProductPersistenceEntities;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "ordersPersistenceEntity")
    private PaymentPersistenceEntity paymentPersistenceEntity;

}
