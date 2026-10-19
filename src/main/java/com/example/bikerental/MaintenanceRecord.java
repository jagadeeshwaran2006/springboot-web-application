package com.example.bikerental;
import jakarta.persistence.*; import java.math.BigDecimal; import java.time.LocalDate;
@Entity @Table(name="maintenance_records")
public class MaintenanceRecord {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @ManyToOne(optional=false) private Bike bike;
 @Column(nullable=false) private String notes;
 private LocalDate serviceDate=LocalDate.now(); private LocalDate nextServiceDate;
 @Column(nullable=false,precision=10,scale=2) private BigDecimal cost=BigDecimal.ZERO;
 public MaintenanceRecord(){} public MaintenanceRecord(Bike b,String n,LocalDate d){bike=b;notes=n;serviceDate=d;}
 public Long getId(){return id;} public Bike getBike(){return bike;} public String getNotes(){return notes;} public LocalDate getServiceDate(){return serviceDate;} public LocalDate getNextServiceDate(){return nextServiceDate;} public void setNextServiceDate(LocalDate v){nextServiceDate=v;} public BigDecimal getCost(){return cost;} public void setCost(BigDecimal v){cost=v;}
}
