package com.carrental.app.model;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String brand;
    @Column(nullable = false)
    private String category;
    @Column(nullable = false)
    private String transmission;
    @Column(nullable = false)
    private String fuelType;
    @Column(nullable = false)
    private Integer seats;
    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal pricePerDay;
    @Column(nullable = false)
    private String location;
    @Column(nullable = false)
    private Double rating;
    @Column(nullable = false, length = 500)
    private String shortDescription;
    @Column(nullable = false, length = 3000)
    private String description;
    @Column(nullable = false, length = 1500)
    private String features;
    @Column(nullable = false, length = 1000)
    private String heroImage;
    @Column(nullable = false, length = 1000)
    private String imageOne;
    @Column(nullable = false, length = 1000)
    private String imageTwo;
    @Column(nullable = false, length = 1000)
    private String imageThree;
    @Column(nullable = false)
    private Boolean available;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
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
