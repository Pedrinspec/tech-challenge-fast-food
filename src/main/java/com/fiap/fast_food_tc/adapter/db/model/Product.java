package com.fiap.fast_food_tc.adapter.db.model;

import jakarta.persistence.*;
import lombok.*;

@Builder
@Entity
@Table(name = "product")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id", nullable = false, unique = true)
    private Integer productId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "product_value")
    private Double productValue;

    @Column(name = "available_indicator")
    private Boolean isAvailable;

    @Column(name = "category_category_id")
    private Integer categoryId;

    @Column(name = "description")
    private String description;

    @Column(name = "image_path")
    private String imagePath;

}
