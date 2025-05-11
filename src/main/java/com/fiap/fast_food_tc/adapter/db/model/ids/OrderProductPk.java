package com.fiap.fast_food_tc.adapter.db.model.ids;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Builder
@EqualsAndHashCode
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class OrderProductPk implements Serializable {

    private Integer orderId;
    private Integer productId;

}
