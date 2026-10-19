package com.example.bikerental;
import org.springframework.scheduling.annotation.Scheduled; import org.springframework.stereotype.Component; import java.time.LocalDateTime; import java.util.List;
@Component public class OverdueAlertScheduler {
 private final RentalRepository rentals; private final UserAccountRepository accounts; private final AppNotificationRepository notifications;
 public OverdueAlertScheduler(RentalRepository r,UserAccountRepository a,AppNotificationRepository n){rentals=r;accounts=a;notifications=n;}
 @Scheduled(fixedDelay=60000) public void alertOverdueRentals(){LocalDateTime now=LocalDateTime.now();List<Rental> all=rentals.findAll();for(Rental r:all){if(!r.isOverdueNotified()&&List.of("BOOKED","ACTIVE").contains(r.getStatus())&&r.getScheduledReturn()!=null&&r.getScheduledReturn().isBefore(now)){accounts.findAll().stream().filter(u->u.getEmail().equalsIgnoreCase(r.getCustomer().getEmail())).findFirst().ifPresent(u->notifications.save(new AppNotification(u.getUsername(),"Overdue reminder: booking "+r.getBookingCode()+" was due to return at "+r.getScheduledReturn()+". Please contact the rental desk.")));r.setOverdueNotified(true);rentals.save(r);}}}
}
