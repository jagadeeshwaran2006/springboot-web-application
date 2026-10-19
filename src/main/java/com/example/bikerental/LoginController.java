package com.example.bikerental;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LoginController {
    private final UserAccountRepository accounts;
    private final CustomerRepository customers;
    private final PasswordEncoder passwordEncoder;
    private final AppNotificationRepository notifications;

    public LoginController(UserAccountRepository accounts, CustomerRepository customers, PasswordEncoder passwordEncoder, AppNotificationRepository notifications) {
        this.accounts = accounts;
        this.customers = customers;
        this.passwordEncoder = passwordEncoder;
        this.notifications = notifications;
    }

    @GetMapping("/login")
    public String login() { return "login"; }

    @GetMapping("/signup")
    public String signup(Model model) {
        if (!model.containsAttribute("signupForm")) model.addAttribute("signupForm", new SignupForm());
        return "signup";
    }

    @PostMapping("/signup")
    public String register(@Valid @ModelAttribute("signupForm") SignupForm form, BindingResult result,
                           Model model, RedirectAttributes flash) {
        if ("admin".equalsIgnoreCase(form.getUsername())) {
            result.rejectValue("username", "reserved", "This username is reserved.");
        }
        if (accounts.existsByUsername(form.getUsername())) result.rejectValue("username", "duplicate", "Username is already taken.");
        if (accounts.existsByEmail(form.getEmail()) || customers.existsByEmail(form.getEmail())) {
            result.rejectValue("email", "duplicate", "An account with this email already exists.");
        }
        if (result.hasErrors()) return "signup";

        UserAccount account = new UserAccount();
        account.setName(form.getName().trim());
        account.setEmail(form.getEmail().trim().toLowerCase());
        account.setPhone(form.getPhone().trim());
        account.setUsername(form.getUsername().trim());
        account.setPassword(passwordEncoder.encode(form.getPassword()));
        account.setRole("CUSTOMER");
        account.setReferralCode("RIDE" + java.util.UUID.randomUUID().toString().substring(0, 6).toUpperCase());
        if (form.getReferralCode() != null && !form.getReferralCode().isBlank()) {
            accounts.findByReferralCode(form.getReferralCode().trim().toUpperCase()).ifPresent(referrer -> {
                referrer.setLoyaltyPoints(referrer.getLoyaltyPoints() + 100);
                accounts.save(referrer);
                notifications.save(new AppNotification(referrer.getUsername(), "Referral bonus: a new customer joined using your code."));
                account.setLoyaltyPoints(50);
            });
        }
        accounts.save(account);

        Customer customer = new Customer();
        customer.setName(account.getName());
        customer.setEmail(account.getEmail());
        customer.setPhone(account.getPhone());
        customers.save(customer);

        flash.addFlashAttribute("signupSuccess", "Account created successfully. Please sign in.");
        return "redirect:/login";
    }

    @GetMapping("/home")
    public String home(Authentication authentication) {
        boolean admin = authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
        return admin ? "redirect:/admin" : "redirect:/customer/dashboard";
    }

    public static class SignupForm {
        @NotBlank(message = "Full name is required") @Size(max = 100)
        private String name;
        @NotBlank(message = "Email is required") @Email(message = "Enter a valid email") @Size(max = 150)
        private String email;
        @NotBlank(message = "Phone is required") @Pattern(regexp = "[0-9+() -]{7,30}", message = "Enter a valid phone number")
        private String phone;
        @NotBlank(message = "Username is required") @Size(min = 4, max = 50, message = "Username must be 4–50 characters")
        @Pattern(regexp = "[A-Za-z0-9_.-]+", message = "Use letters, numbers, dot, underscore or hyphen")
        private String username;
        @NotBlank(message = "Password is required") @Size(min = 8, max = 72, message = "Password must be 8–72 characters")
        private String password;
        private String referralCode;
        public String getReferralCode() { return referralCode; } public void setReferralCode(String referralCode) { this.referralCode = referralCode; }
        public String getName() { return name; } public void setName(String name) { this.name = name; }
        public String getEmail() { return email; } public void setEmail(String email) { this.email = email; }
        public String getPhone() { return phone; } public void setPhone(String phone) { this.phone = phone; }
        public String getUsername() { return username; } public void setUsername(String username) { this.username = username; }
        public String getPassword() { return password; } public void setPassword(String password) { this.password = password; }
    }
}
