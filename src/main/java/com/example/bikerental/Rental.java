package com.example.bikerental;
import jakarta.persistence.*; import jakarta.validation.constraints.NotNull; import java.math.BigDecimal; import java.time.LocalDateTime; import java.util.UUID;
@Entity @Table(name="rentals")
public class Rental {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @ManyToOne(optional=false) @JoinColumn(name="bike_id",nullable=false) private Bike bike;
 @ManyToOne(optional=false) @JoinColumn(name="customer_id",nullable=false) private Customer customer;
 @NotNull @Column(nullable=false) private LocalDateTime startTime;
 private LocalDateTime endTime;
 @Column(nullable=false,precision=10,scale=2) private BigDecimal totalAmount=BigDecimal.ZERO;
 @Column(nullable=false) private String status="ACTIVE";
 private LocalDateTime scheduledPickup; private LocalDateTime scheduledReturn;
 @Column(unique=true) private String bookingCode;
 @Column(nullable=false,precision=10,scale=2) private BigDecimal depositAmount=new BigDecimal("1000.00");
 @Column(nullable=false) private String depositStatus="PENDING";
 @Column(length=50) private String couponCode;
 @Column(nullable=false) private boolean paymentPaid=false;
 @Column(nullable=false) private boolean returnConditionOk=true;
 @Column(nullable=false) private boolean agreementAccepted=false;
 @Column(nullable=false) private boolean overdueNotified=false;
 @Column(length=1000) private String conditionNotes;
 @Column(length=1000) private String conditionPhotoUrl;
 public Rental(){ if(bookingCode==null) bookingCode="BR-"+UUID.randomUUID().toString().substring(0,8).toUpperCase(); }
 public Long getId(){return id;} public void setId(Long v){id=v;} public Bike getBike(){return bike;} public void setBike(Bike v){bike=v;} public Customer getCustomer(){return customer;} public void setCustomer(Customer v){customer=v;} public LocalDateTime getStartTime(){return startTime;} public void setStartTime(LocalDateTime v){startTime=v;} public LocalDateTime getEndTime(){return endTime;} public void setEndTime(LocalDateTime v){endTime=v;} public BigDecimal getTotalAmount(){return totalAmount;} public void setTotalAmount(BigDecimal v){totalAmount=v;} public String getStatus(){return status;} public void setStatus(String v){status=v;} public LocalDateTime getScheduledPickup(){return scheduledPickup;} public void setScheduledPickup(LocalDateTime v){scheduledPickup=v;} public LocalDateTime getScheduledReturn(){return scheduledReturn;} public void setScheduledReturn(LocalDateTime v){scheduledReturn=v;} public String getBookingCode(){return bookingCode;} public void setBookingCode(String v){bookingCode=v;} public BigDecimal getDepositAmount(){return depositAmount;} public void setDepositAmount(BigDecimal v){depositAmount=v;} public String getDepositStatus(){return depositStatus;} public void setDepositStatus(String v){depositStatus=v;} public String getCouponCode(){return couponCode;} public void setCouponCode(String v){couponCode=v;} public boolean isPaymentPaid(){return paymentPaid;} public void setPaymentPaid(boolean v){paymentPaid=v;} public boolean isReturnConditionOk(){return returnConditionOk;} public void setReturnConditionOk(boolean v){returnConditionOk=v;} public boolean isAgreementAccepted(){return agreementAccepted;} public void setAgreementAccepted(boolean v){agreementAccepted=v;} public boolean isOverdueNotified(){return overdueNotified;} public void setOverdueNotified(boolean v){overdueNotified=v;} public String getConditionNotes(){return conditionNotes;} public void setConditionNotes(String v){conditionNotes=v;} public String getConditionPhotoUrl(){return conditionPhotoUrl;} public void setConditionPhotoUrl(String v){conditionPhotoUrl=v;}
}
