package com.example.bikerental;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    boolean existsByEmail(String email);
    java.util.Optional<Customer> findByEmail(String email);
}
