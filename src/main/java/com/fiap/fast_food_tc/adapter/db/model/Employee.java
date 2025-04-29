package com.fiap.fast_food_tc.adapter.db.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "employee")
@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id", nullable = false, unique = true)
    private Long employeeId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "document_number", nullable = false, unique = true, length = 11)
    private String documentNumber;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "manager_indicator", nullable = false)
    private Boolean isManager;
}
