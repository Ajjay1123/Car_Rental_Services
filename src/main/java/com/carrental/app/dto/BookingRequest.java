package com.carrental.app.dto;

import org.springframework.web.multipart.MultipartFile;

public class BookingRequest {
    private Long carId;
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
    private MultipartFile aadharDocument;
    private MultipartFile panDocument;
    private MultipartFile licenseDocument;

    public Long getCarId() { return carId; }
    public void setCarId(Long carId) { this.carId = carId; }
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
    public MultipartFile getAadharDocument() { return aadharDocument; }
    public void setAadharDocument(MultipartFile aadharDocument) { this.aadharDocument = aadharDocument; }
    public MultipartFile getPanDocument() { return panDocument; }
    public void setPanDocument(MultipartFile panDocument) { this.panDocument = panDocument; }
    public MultipartFile getLicenseDocument() { return licenseDocument; }
    public void setLicenseDocument(MultipartFile licenseDocument) { this.licenseDocument = licenseDocument; }
}
