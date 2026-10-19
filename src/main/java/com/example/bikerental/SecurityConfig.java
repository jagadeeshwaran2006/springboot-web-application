package com.example.bikerental;
import org.springframework.context.annotation.Bean; import org.springframework.context.annotation.Configuration; import org.springframework.http.HttpMethod; import org.springframework.security.config.annotation.web.builders.HttpSecurity; import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder; import org.springframework.security.crypto.password.PasswordEncoder; import org.springframework.security.web.SecurityFilterChain;
@Configuration public class SecurityConfig {
 @Bean PasswordEncoder passwordEncoder(){return new BCryptPasswordEncoder();}
 @Bean SecurityFilterChain securityFilterChain(HttpSecurity http)throws Exception{
  http.authorizeHttpRequests(auth->auth
   .requestMatchers("/","/catalog","/bike/**","/compare","/plans","/rental-terms","/login","/signup","/signup/**","/css/**","/js/**","/images/**","/error").permitAll()
   .requestMatchers(HttpMethod.GET,"/customer/reviews","/customer/trip-planner","/customer/assistant").permitAll()
   .requestMatchers(HttpMethod.POST,"/customer/trip-planner","/customer/assistant").permitAll()
   .requestMatchers("/admin/**").hasRole("ADMIN")
   .requestMatchers("/customer/**").hasRole("CUSTOMER")
   .anyRequest().authenticated())
   .formLogin(form->form.loginPage("/login").defaultSuccessUrl("/home",true).failureUrl("/login?error").permitAll())
   .logout(logout->logout.logoutUrl("/logout").logoutSuccessUrl("/login?logout").permitAll());
  return http.build();
 }
}
