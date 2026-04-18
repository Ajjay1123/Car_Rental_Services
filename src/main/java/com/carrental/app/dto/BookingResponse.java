package com.carrental.app.dto;

import com.carrental.app.model.Booking;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class BookingResponse {
    private Long id;
    private String customerName;
    private String username;
    private String carName;
    private String carBrand;
    private String pickupDate;
    private String returnDate;
    private String pickupLocation;
    private String dropoffLocation;
    private String contactNumber;
    private String email;
    private String address;
    private String aadharNumber;
    private String panNumber;
    private String licenseNumber;
    private String aadharDocumentUrl;
    private String panDocumentUrl;
    private String licenseDocumentUrl;
    private String status;
    private BigDecimal totalAmount;
    private LocalDateTime createdAt;

    public static BookingResponse from(Booking booking) {
        BookingResponse response = new BookingResponse();
        response.setId(booking.getId());
        response.setCustomerName(booking.getUser().getFullName());
        response.setUsername(booking.getUser().getUsername());
        response.setCarName(booking.getCar().getName());
        response.setCarBrand(booking.getCar().getBrand());
        response.setPickupDate(booking.getPickupDate().toString());
        response.setReturnDate(booking.getReturnDate().toString());
        response.setPickupLocation(booking.getPickupLocation());
        response.setDropoffLocation(booking.getDropoffLocation());
        response.setContactNumber(booking.getContactNumber());
        response.setEmail(booking.getEmail());
        response.setAddress(booking.getAddress());
        response.setAadharNumber(booking.getAadharNumber());
        response.setPanNumber(booking.getPanNumber());
        response.setLicenseNumber(booking.getLicenseNumber());
        response.setAadharDocumentUrl(booking.getAadharDocumentPath());
        response.setPanDocumentUrl(booking.getPanDocumentPath());
        response.setLicenseDocumentUrl(booking.getLicenseDocumentPath());
        response.setStatus(booking.getStatus());
        response.setTotalAmount(booking.getTotalAmount());
        response.setCreatedAt(booking.getCreatedAt());
        return response;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getCarName() { return carName; }
    public void setCarName(String carName) { this.carName = carName; }
    public String getCarBrand() { return carBrand; }
    public void setCarBrand(String carBrand) { this.carBrand = carBrand; }
    public String getPickupDate() { return pickupDate; }
    public void setPickupDate(String pickupDate) { this.pickupDate = pickupDate; }
    public String getReturnDate() { return returnDate; }
    public void setReturnDate(String returnDate) { this.returnDate = returnDate; }
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
    public String getAadharDocumentUrl() { return aadharDocumentUrl; }
    public void setAadharDocumentUrl(String aadharDocumentUrl) { this.aadharDocumentUrl = aadharDocumentUrl; }
    public String getPanDocumentUrl() { return panDocumentUrl; }
    public void setPanDocumentUrl(String panDocumentUrl) { this.panDocumentUrl = panDocumentUrl; }
    public String getLicenseDocumentUrl() { return licenseDocumentUrl; }
    public void setLicenseDocumentUrl(String licenseDocumentUrl) { this.licenseDocumentUrl = licenseDocumentUrl; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public BigDecimal getTotalAmount() { return totalAmount; }
    public void setTotalAmount(BigDecimal totalAmount) { this.totalAmount = totalAmount; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
