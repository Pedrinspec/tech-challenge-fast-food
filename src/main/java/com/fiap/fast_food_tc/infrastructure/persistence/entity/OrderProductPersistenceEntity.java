package com.fiap.fast_food_tc.infrastructure.persistence.entity;

import com.fiap.fast_food_tc.infrastructure.persistence.entity.ids.OrderProductPk;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "order_product")
public class OrderProductPersistenceEntity {

    @EmbeddedId
    private OrderProductPk id;

    @ManyToOne
    @MapsId("orderId")
    @JoinColumn(name = "order_id")
    private OrdersPersistenceEntity ordersPersistenceEntity;

    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "product_id")
    private ProductPersistenceEntity productPersistenceEntity;

    private Integer productQuantity;
    private BigDecimal productTotalAmount;

}
