package com.example.bikerental;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserAccountRepository accounts;
    private final PasswordEncoder encoder;

    public CustomUserDetailsService(UserAccountRepository accounts, PasswordEncoder encoder) {
        this.accounts = accounts;
        this.encoder = encoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // The administrator is a separate, built-in account; customers are loaded from MySQL.
        if ("admin".equalsIgnoreCase(username)) {
            return User.withUsername("admin").password(encoder.encode("Admin@123")).roles("ADMIN").build();
        }
        UserAccount account = accounts.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Account not found"));
        return User.withUsername(account.getUsername()).password(account.getPassword())
                .roles(account.getRole()).build();
    }
}
