package com.example.bikerental;
import org.springframework.security.core.Authentication; import org.springframework.web.bind.annotation.ControllerAdvice; import org.springframework.web.bind.annotation.ModelAttribute;
@ControllerAdvice public class GlobalModelAdvice {
 @ModelAttribute("currentUser") public String currentUser(Authentication a){return a==null||a.getAuthorities().stream().anyMatch(x->x.getAuthority().equals("ROLE_ANONYMOUS"))?"":a.getName();}
 @ModelAttribute("isAdmin") public boolean isAdmin(Authentication a){return a!=null&&a.getAuthorities().stream().anyMatch(x->x.getAuthority().equals("ROLE_ADMIN"));}
 @ModelAttribute("isCustomer") public boolean isCustomer(Authentication a){return a!=null&&a.getAuthorities().stream().anyMatch(x->x.getAuthority().equals("ROLE_CUSTOMER"));}
}
