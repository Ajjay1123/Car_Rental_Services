package com.carrental.app.service;

import com.carrental.app.dto.CarRequest;
import com.carrental.app.model.Car;
import com.carrental.app.model.Role;
import com.carrental.app.model.User;
import com.carrental.app.repository.BookingRepository;
import com.carrental.app.repository.CarRepository;
import javax.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class CarAdminService {
    private final CarRepository carRepository;
    private final BookingRepository bookingRepository;
    private final AuthService authService;

    public CarAdminService(CarRepository carRepository, BookingRepository bookingRepository, AuthService authService) {
        this.carRepository = carRepository;
        this.bookingRepository = bookingRepository;
        this.authService = authService;
    }

    public Car createCar(CarRequest request, HttpSession session) {
        requireAdmin(session);
        Car car = new Car();
        apply(car, request);
        return carRepository.save(car);
    }

    public Car updateCar(Long carId, CarRequest request, HttpSession session) {
        requireAdmin(session);
        Car car = carRepository.findById(carId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Car not found"));
        apply(car, request);
        return carRepository.save(car);
    }

    public void deleteCar(Long carId, HttpSession session) {
        requireAdmin(session);
        Car car = carRepository.findById(carId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Car not found"));
        if (bookingRepository.existsByCarId(carId)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "This car already has bookings, so it cannot be deleted");
        }
        carRepository.delete(car);
    }

    private void requireAdmin(HttpSession session) {
        User user = authService.getAuthenticatedUser(session);
        if (user.getRole() != Role.ADMIN) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Admin access required");
        }
    }

    private void apply(Car car, CarRequest request) {
        validate(request);
        car.setName(request.getName().trim());
        car.setBrand(request.getBrand().trim());
        car.setCategory(request.getCategory().trim());
        car.setTransmission(request.getTransmission().trim());
        car.setFuelType(request.getFuelType().trim());
        car.setSeats(request.getSeats());
        car.setPricePerDay(request.getPricePerDay());
        car.setLocation(request.getLocation().trim());
        car.setRating(request.getRating());
        car.setShortDescription(request.getShortDescription().trim());
        car.setDescription(request.getDescription().trim());
        car.setFeatures(request.getFeatures().trim());
        car.setHeroImage(request.getHeroImage().trim());
        car.setImageOne(request.getImageOne().trim());
        car.setImageTwo(request.getImageTwo().trim());
        car.setImageThree(request.getImageThree().trim());
        car.setAvailable(Boolean.TRUE.equals(request.getAvailable()));
    }

    private void validate(CarRequest request) {
        requireText(request.getName(), "Car name is required");
        requireText(request.getBrand(), "Brand is required");
        requireText(request.getCategory(), "Category is required");
        requireText(request.getTransmission(), "Transmission is required");
        requireText(request.getFuelType(), "Fuel type is required");
        requireText(request.getLocation(), "Location is required");
        requireText(request.getShortDescription(), "Short description is required");
        requireText(request.getDescription(), "Description is required");
        requireText(request.getFeatures(), "Features are required");
        requireText(request.getHeroImage(), "Hero image is required");
        requireText(request.getImageOne(), "Image one is required");
        requireText(request.getImageTwo(), "Image two is required");
        requireText(request.getImageThree(), "Image three is required");
        if (request.getSeats() == null || request.getSeats() < 2) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Seat count must be at least 2");
        }
        if (request.getPricePerDay() == null || request.getPricePerDay().doubleValue() <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Price per day must be greater than 0");
        }
        if (request.getRating() == null || request.getRating() < 0 || request.getRating() > 5) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Rating must be between 0 and 5");
        }
    }

    private void requireText(String value, String message) {
        if (value == null || value.trim().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, message);
        }
    }
}
