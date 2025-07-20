package com.fiap.fast_food_tc.infrastructure.persistence.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter
@Entity
@Table(name = "customer")
@AllArgsConstructor
@NoArgsConstructor
public class CustomerPersistenceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id", nullable = false, unique = true)
    private Integer customerId;

    @Column(name = "document_number", nullable = false, unique = true, length = 11)
    private String documentNumber;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "email", nullable = false, unique = true, length = 45)
    private String email;

    @OneToMany(mappedBy = "customerPersistenceEntity")
    private List<OrdersPersistenceEntity> orders;

}
