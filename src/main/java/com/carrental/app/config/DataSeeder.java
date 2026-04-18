package com.carrental.app.config;

import com.carrental.app.model.Car;
import com.carrental.app.model.Role;
import com.carrental.app.model.User;
import com.carrental.app.repository.CarRepository;
import com.carrental.app.repository.UserRepository;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataSeeder implements CommandLineRunner {

    private final UserRepository userRepository;
    private final CarRepository carRepository;
    private final PasswordEncoder passwordEncoder;

    public DataSeeder(UserRepository userRepository, CarRepository carRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.carRepository = carRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        if (userRepository.count() == 0) {
            userRepository.save(new User(null, "System Admin", "admin", passwordEncoder.encode("admin123"), Role.ADMIN, "admin@swaysarthi.com", "9876543210"));
            userRepository.save(new User(null, "Rahul Customer", "user", passwordEncoder.encode("user123"), Role.USER, "user@swaysarthi.com", "9123456780"));
        } else {
            upgradeExistingPasswords();
        }

        if (carRepository.count() == 0) {
            carRepository.saveAll(Arrays.asList(
                    buildCar("BMW X5", "BMW", "Luxury SUV", "Automatic", "Diesel", 5, "9200", "Mumbai", 4.9,
                            "Premium comfort, bold road presence and elite cabin finish.",
                            "Drive the BMW X5 for business travel, family tours, and premium city rides with advanced safety, leather interiors, panoramic sunroof, and strong highway performance.",
                            "Panoramic Sunroof,ADAS Safety,Leather Seats,Ambient Lighting,360 Camera",
                            "https://images.unsplash.com/photo-1555215695-3004980ad54e?auto=format&fit=crop&w=1200&q=80",
                            "https://images.unsplash.com/photo-1494976388531-d1058494cdd8?auto=format&fit=crop&w=1200&q=80",
                            "https://images.unsplash.com/photo-1503376780353-7e6692767b70?auto=format&fit=crop&w=1200&q=80",
                            "https://images.unsplash.com/photo-1511919884226-fd3cad34687c?auto=format&fit=crop&w=1200&q=80"),
                    buildCar("Hyundai Creta", "Hyundai", "Family SUV", "Automatic", "Petrol", 5, "4200", "Pune", 4.7,
                            "Comfortable urban SUV with practical features and spacious cabin.",
                            "Hyundai Creta is ideal for weekend escapes and daily rental needs with smooth automatic drive, stylish cabin, premium infotainment, and strong fuel efficiency.",
                            "Wireless Charging,Rear AC Vents,Touchscreen,ABS,Reverse Camera",
                            "https://images.unsplash.com/photo-1549399542-7e3f8b79c341?auto=format&fit=crop&w=1200&q=80",
                            "https://images.unsplash.com/photo-1544636331-e26879cd4d9b?auto=format&fit=crop&w=1200&q=80",
                            "https://images.unsplash.com/photo-1504215680853-026ed2a45def?auto=format&fit=crop&w=1200&q=80",
                            "https://images.unsplash.com/photo-1492144534655-ae79c964c9d7?auto=format&fit=crop&w=1200&q=80"),
                    buildCar("Mahindra Thar", "Mahindra", "Adventure SUV", "Manual", "Diesel", 4, "5100", "Goa", 4.8,
                            "Rugged open-road SUV built for beach drives and off-road trips.",
                            "The Thar adds excitement to every trip with high ground clearance, iconic styling, commanding seating, and dependable performance for outdoor adventures.",
                            "4x4 Capability,Removable Roof,Touchscreen,All-Terrain Tyres,Cruise Control",
                            "https://images.unsplash.com/photo-1502877338535-766e1452684a?auto=format&fit=crop&w=1200&q=80",
                            "https://images.unsplash.com/photo-1489824904134-891ab64532f1?auto=format&fit=crop&w=1200&q=80",
                            "https://images.unsplash.com/photo-1519641471654-76ce0107ad1b?auto=format&fit=crop&w=1200&q=80",
                            "https://images.unsplash.com/photo-1533473359331-0135ef1b58bf?auto=format&fit=crop&w=1200&q=80"),
                    buildCar("Toyota Fortuner", "Toyota", "Executive SUV", "Automatic", "Diesel", 7, "6800", "Delhi", 4.8,
                            "Confident highway cruiser with strong road presence and roomy interiors.",
                            "Toyota Fortuner is designed for airport transfers, family drives, and long-distance comfort with captain-style seating, commanding visibility, and dependable diesel power.",
                            "7 Seats,Touchscreen Infotainment,Cruise Control,Rear Camera,Climate Control",
                            "https://images.unsplash.com/photo-1503736334956-4c8f8e92946d?auto=format&fit=crop&w=1200&q=80",
                            "https://images.unsplash.com/photo-1492144534655-ae79c964c9d7?auto=format&fit=crop&w=1200&q=80",
                            "https://images.unsplash.com/photo-1519641471654-76ce0107ad1b?auto=format&fit=crop&w=1200&q=80",
                            "https://images.unsplash.com/photo-1533473359331-0135ef1b58bf?auto=format&fit=crop&w=1200&q=80")
            ));
        }
    }

    private void upgradeExistingPasswords() {
        List<User> users = userRepository.findAll();
        boolean changed = false;
        for (User user : users) {
            String password = user.getPassword();
            if (password != null && !isEncoded(password)) {
                user.setPassword(passwordEncoder.encode(password));
                changed = true;
            }
        }
        if (changed) {
            userRepository.saveAll(users);
        }
    }

    private boolean isEncoded(String password) {
        return password.startsWith("$2a$") || password.startsWith("$2b$") || password.startsWith("$2y$");
    }

    private Car buildCar(String name, String brand, String category, String transmission, String fuelType,
                         Integer seats, String price, String location, Double rating,
                         String shortDescription, String description, String features,
                         String heroImage, String imageOne, String imageTwo, String imageThree) {
        Car car = new Car();
        car.setName(name);
        car.setBrand(brand);
        car.setCategory(category);
        car.setTransmission(transmission);
        car.setFuelType(fuelType);
        car.setSeats(seats);
        car.setPricePerDay(new BigDecimal(price));
        car.setLocation(location);
        car.setRating(rating);
        car.setShortDescription(shortDescription);
        car.setDescription(description);
        car.setFeatures(features);
        car.setHeroImage(heroImage);
        car.setImageOne(imageOne);
        car.setImageTwo(imageTwo);
        car.setImageThree(imageThree);
        car.setAvailable(true);
        return car;
    }
}
