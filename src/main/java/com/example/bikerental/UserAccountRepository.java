package com.example.bikerental;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {
    Optional<UserAccount> findByUsername(String username);
    Optional<UserAccount> findByReferralCode(String referralCode);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
