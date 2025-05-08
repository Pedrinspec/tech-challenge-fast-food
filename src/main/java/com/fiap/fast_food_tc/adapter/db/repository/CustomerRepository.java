package com.fiap.fast_food_tc.adapter.db.repository;

import com.fiap.fast_food_tc.adapter.db.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    @Query("SELECT c FROM Customer c LEFT JOIN FETCH c.orders WHERE c.documentNumber = :documentNumber")
    Optional<Customer> findByDocumentNumber(@Param("documentNumber") String documentNumber);

//    // query JPQL
//    @Query("SELECT u FROM User u WHERE u.nome LIKE %:nome%")
//    List<User> buscarPorNome(@Param("nome") String nome);
//
//    // query nativa
//    @Query(value = "SELECT * FROM user WHERE email = :email", nativeQuery = true)
//    Optional<User> buscarPorEmailNativo(@Param("email") String email);

}
