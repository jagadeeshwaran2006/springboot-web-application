package com.example.bikerental;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BikeRepository extends JpaRepository<Bike, Long> {
    List<Bike> findByAvailableTrue();
    boolean existsByRegistrationNumberAndIdNot(String registrationNumber, Long id);
}
