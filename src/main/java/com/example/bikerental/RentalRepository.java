package com.example.bikerental;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface RentalRepository extends JpaRepository<Rental, Long> {
    List<Rental> findAllByOrderByStartTimeDesc();
    boolean existsByBikeIdAndStatus(Long bikeId, String status);
    List<Rental> findAllByCustomerEmailOrderByStartTimeDesc(String email);
    java.util.Optional<Rental> findByIdAndCustomerEmail(Long id, String email);
}
