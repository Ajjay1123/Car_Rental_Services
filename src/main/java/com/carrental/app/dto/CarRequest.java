package com.carrental.app.dto;

import java.math.BigDecimal;

public class CarRequest {
    private String name;
    private String brand;
    private String category;
    private String transmission;
    private String fuelType;
    private Integer seats;
    private BigDecimal pricePerDay;
    private String location;
    private Double rating;
    private String shortDescription;
    private String description;
    private String features;
    private String heroImage;
    private String imageOne;
    private String imageTwo;
    private String imageThree;
    private Boolean available;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getBrand() { return brand; }
    public void setBrand(String brand) { this.brand = brand; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public String getTransmission() { return transmission; }
    public void setTransmission(String transmission) { this.transmission = transmission; }
    public String getFuelType() { return fuelType; }
    public void setFuelType(String fuelType) { this.fuelType = fuelType; }
    public Integer getSeats() { return seats; }
    public void setSeats(Integer seats) { this.seats = seats; }
    public BigDecimal getPricePerDay() { return pricePerDay; }
    public void setPricePerDay(BigDecimal pricePerDay) { this.pricePerDay = pricePerDay; }
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    public Double getRating() { return rating; }
    public void setRating(Double rating) { this.rating = rating; }
    public String getShortDescription() { return shortDescription; }
    public void setShortDescription(String shortDescription) { this.shortDescription = shortDescription; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getFeatures() { return features; }
    public void setFeatures(String features) { this.features = features; }
    public String getHeroImage() { return heroImage; }
    public void setHeroImage(String heroImage) { this.heroImage = heroImage; }
    public String getImageOne() { return imageOne; }
    public void setImageOne(String imageOne) { this.imageOne = imageOne; }
    public String getImageTwo() { return imageTwo; }
    public void setImageTwo(String imageTwo) { this.imageTwo = imageTwo; }
    public String getImageThree() { return imageThree; }
    public void setImageThree(String imageThree) { this.imageThree = imageThree; }
    public Boolean getAvailable() { return available; }
    public void setAvailable(Boolean available) { this.available = available; }
}
