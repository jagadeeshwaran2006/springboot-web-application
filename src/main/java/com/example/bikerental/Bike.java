package com.example.bikerental;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;

@Entity @Table(name="bikes")
public class Bike {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @NotBlank(message="Bike name is required") @Column(nullable=false) private String name;
 @NotBlank(message="Brand is required") @Column(nullable=false) private String brand;
 @NotBlank(message="Registration number is required") @Column(nullable=false,unique=true) private String registrationNumber;
 @NotNull(message="Hourly rate is required") @DecimalMin(value="0.01",message="Rate must be positive") @Column(nullable=false,precision=10,scale=2) private BigDecimal hourlyRate;
 @NotBlank(message="Bike type is required") @Column(nullable=false) private String bikeType;
 @Column(nullable=false) private boolean available=true;
 @Column(length=1000) private String description;
 @Column(length=1000) private String imageUrl;
 @Column(length=150, columnDefinition="varchar(150) default 'Chennai'") private String location="Chennai";
 @Column(nullable=false,precision=10,scale=2,columnDefinition="decimal(10,2) default 800.00") private BigDecimal dailyRate=new BigDecimal("800.00");
 @Column(nullable=false,columnDefinition="integer default 40") private int mileage=40;
 @Column(nullable=false,columnDefinition="varchar(30) default 'READY'") private String maintenanceStatus="READY";
 public Bike(){}
 public Long getId(){return id;} public void setId(Long v){id=v;} public String getName(){return name;} public void setName(String v){name=v;} public String getBrand(){return brand;} public void setBrand(String v){brand=v;} public String getRegistrationNumber(){return registrationNumber;} public void setRegistrationNumber(String v){registrationNumber=v;} public BigDecimal getHourlyRate(){return hourlyRate;} public void setHourlyRate(BigDecimal v){hourlyRate=v;} public String getBikeType(){return bikeType;} public void setBikeType(String v){bikeType=v;} public boolean isAvailable(){return available && (maintenanceStatus==null || maintenanceStatus.isBlank() || "READY".equalsIgnoreCase(maintenanceStatus));} public void setAvailable(boolean v){available=v;} public String getDescription(){return description;} public void setDescription(String v){description=v;} public String getImageUrl(){return imageUrl;} public void setImageUrl(String v){imageUrl=v;} public String getLocation(){return location==null?"Chennai":location;} public void setLocation(String v){location=v;} public BigDecimal getDailyRate(){return dailyRate==null?new BigDecimal("800.00"):dailyRate;} public void setDailyRate(BigDecimal v){dailyRate=v;} public int getMileage(){return mileage<=0?40:mileage;} public void setMileage(int v){mileage=v;} public String getMaintenanceStatus(){return maintenanceStatus==null?"READY":maintenanceStatus;} public void setMaintenanceStatus(String v){maintenanceStatus=v;}
}
