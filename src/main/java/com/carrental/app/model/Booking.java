package com.carrental.app.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

@Entity
@Table(name = "bookings")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "car_id", nullable = false)
    private Car car;
    @Column(nullable = false)
    private LocalDate pickupDate;
    @Column(nullable = false)
    private LocalDate returnDate;
    @Column(nullable = false)
    private String pickupLocation;
    @Column(nullable = false)
    private String dropoffLocation;
    @Column(nullable = false)
    private String contactNumber;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false, length = 1000)
    private String address;
    @Column(nullable = false)
    private String aadharNumber;
    @Column(nullable = false)
    private String panNumber;
    @Column(nullable = false)
    private String licenseNumber;
    @Column(nullable = false, length = 1000)
    private String aadharDocumentPath;
    @Column(nullable = false, length = 1000)
    private String panDocumentPath;
    @Column(nullable = false, length = 1000)
    private String licenseDocumentPath;
    @Column(nullable = false)
    private String status;
    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal totalAmount;
    @Column(nullable = false)
    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    public Car getCar() { return car; }
    public void setCar(Car car) { this.car = car; }
    public LocalDate getPickupDate() { return pickupDate; }
    public void setPickupDate(LocalDate pickupDate) { this.pickupDate = pickupDate; }
    public LocalDate getReturnDate() { return returnDate; }
    public void setReturnDate(LocalDate returnDate) { this.returnDate = returnDate; }
    public String getPickupLocation() { return pickupLocation; }
    public void setPickupLocation(String pickupLocation) { this.pickupLocation = pickupLocation; }
    public String getDropoffLocation() { return dropoffLocation; }
    public void setDropoffLocation(String dropoffLocation) { this.dropoffLocation = dropoffLocation; }
    public String getContactNumber() { return contactNumber; }
    public void setContactNumber(String contactNumber) { this.contactNumber = contactNumber; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public String getAadharNumber() { return aadharNumber; }
    public void setAadharNumber(String aadharNumber) { this.aadharNumber = aadharNumber; }
    public String getPanNumber() { return panNumber; }
    public void setPanNumber(String panNumber) { this.panNumber = panNumber; }
    public String getLicenseNumber() { return licenseNumber; }
    public void setLicenseNumber(String licenseNumber) { this.licenseNumber = licenseNumber; }
    public String getAadharDocumentPath() { return aadharDocumentPath; }
    public void setAadharDocumentPath(String aadharDocumentPath) { this.aadharDocumentPath = aadharDocumentPath; }
    public String getPanDocumentPath() { return panDocumentPath; }
    public void setPanDocumentPath(String panDocumentPath) { this.panDocumentPath = panDocumentPath; }
    public String getLicenseDocumentPath() { return licenseDocumentPath; }
    public void setLicenseDocumentPath(String licenseDocumentPath) { this.licenseDocumentPath = licenseDocumentPath; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public BigDecimal getTotalAmount() { return totalAmount; }
    public void setTotalAmount(BigDecimal totalAmount) { this.totalAmount = totalAmount; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
