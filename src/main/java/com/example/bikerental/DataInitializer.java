package com.example.bikerental;
import org.springframework.boot.CommandLineRunner; import org.springframework.context.annotation.Bean; import org.springframework.context.annotation.Configuration; import java.math.BigDecimal; import java.time.LocalDate;
@Configuration public class DataInitializer {
 @Bean CommandLineRunner seedDemoData(BikeRepository bikes, CouponRepository coupons){return args->{
  if(bikes.count()==0){
   add(bikes,"Activa 6G","Honda","TN01AB1001","Scooter",55,650,45,"Chennai Central","Easy city scooter with comfortable seating and practical storage.","https://images.unsplash.com/photo-1558981806-ec527fa84c39?auto=format&fit=crop&w=1000&q=80");
   add(bikes,"Classic 350","Royal Enfield","TN01AB1002","Touring",110,1100,35,"T. Nagar","Relaxed riding position suited to weekend trips and scenic roads.","https://images.unsplash.com/photo-1558981403-c5f9899a28bc?auto=format&fit=crop&w=1000&q=80");
   add(bikes,"Pulsar N160","Bajaj","TN01AB1003","Commuter",85,850,40,"Adyar","Balanced everyday motorcycle with confident handling.","https://images.unsplash.com/photo-1558981806-ec527fa84c39?auto=format&fit=crop&w=1000&q=80");
   add(bikes,"Apache RTR 200","TVS","TN01AB1004","Sport",100,1000,38,"Velachery","Responsive street bike for riders who enjoy a sportier feel.","https://images.unsplash.com/photo-1558981403-c5f9899a28bc?auto=format&fit=crop&w=1000&q=80");
   add(bikes,"Himalayan 450","Royal Enfield","TN01AB1005","Adventure",160,1600,30,"Anna Nagar","Adventure-style bike for longer rides and mixed road conditions.","https://images.unsplash.com/photo-1558981806-ec527fa84c39?auto=format&fit=crop&w=1000&q=80");
   add(bikes,"iQube","TVS","TN01AB1006","Electric",70,700,80,"OMR","Quiet electric scooter for local city trips. Mileage field represents an approximate range score for this demo.","https://images.unsplash.com/photo-1558981403-c5f9899a28bc?auto=format&fit=crop&w=1000&q=80");
  }
  if(coupons.count()==0){coupons.save(new Coupon("RIDE10",10,LocalDate.now().plusMonths(3)));coupons.save(new Coupon("WEEKEND15",15,LocalDate.now().plusMonths(1)));}
 };}
 private void add(BikeRepository repo,String name,String brand,String reg,String type,int hourly,int daily,int mileage,String location,String description,String image){Bike b=new Bike();b.setName(name);b.setBrand(brand);b.setRegistrationNumber(reg);b.setBikeType(type);b.setHourlyRate(BigDecimal.valueOf(hourly));b.setDailyRate(BigDecimal.valueOf(daily));b.setMileage(mileage);b.setLocation(location);b.setDescription(description);b.setImageUrl(image);b.setAvailable(true);b.setMaintenanceStatus("READY");repo.save(b);}
}
