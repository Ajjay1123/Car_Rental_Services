package com.carrental.app.service;

import com.carrental.app.dto.BookingRequest;
import com.carrental.app.dto.BookingResponse;
import com.carrental.app.model.Booking;
import com.carrental.app.model.Car;
import com.carrental.app.model.Role;
import com.carrental.app.model.User;
import com.carrental.app.repository.BookingRepository;
import com.carrental.app.repository.CarRepository;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class BookingService {
    private final BookingRepository bookingRepository;
    private final CarRepository carRepository;
    private final AuthService authService;
    private final FileStorageService fileStorageService;

    public BookingService(BookingRepository bookingRepository, CarRepository carRepository,
                          AuthService authService, FileStorageService fileStorageService) {
        this.bookingRepository = bookingRepository;
        this.carRepository = carRepository;
        this.authService = authService;
        this.fileStorageService = fileStorageService;
    }

    public BookingResponse createBooking(BookingRequest request, HttpSession session) {
        User user = authService.getAuthenticatedUser(session);
        if (user.getRole() != Role.USER) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Only users can create bookings");
        }
        validateRequest(request);
        Car car = carRepository.findById(request.getCarId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Car not found"));
        if (!Boolean.TRUE.equals(car.getAvailable())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Selected car is currently unavailable");
        }
        LocalDate pickupDate = LocalDate.parse(request.getPickupDate());
        LocalDate returnDate = LocalDate.parse(request.getReturnDate());
        if (returnDate.isBefore(pickupDate)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Return date cannot be before pickup date");
        }
        if (pickupDate.isBefore(LocalDate.now())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Pickup date cannot be in the past");
        }
        if (bookingRepository.existsByCarIdAndPickupDateLessThanEqualAndReturnDateGreaterThanEqual(
                car.getId(), returnDate, pickupDate)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Car is already booked for the selected dates");
        }
        long days = Math.max(1, ChronoUnit.DAYS.between(pickupDate, returnDate));

        Booking booking = new Booking();
        booking.setUser(user);
        booking.setCar(car);
        booking.setPickupDate(pickupDate);
        booking.setReturnDate(returnDate);
        booking.setPickupLocation(request.getPickupLocation());
        booking.setDropoffLocation(request.getDropoffLocation());
        booking.setContactNumber(request.getContactNumber());
        booking.setEmail(request.getEmail());
        booking.setAddress(request.getAddress());
        booking.setAadharNumber(request.getAadharNumber());
        booking.setPanNumber(request.getPanNumber());
        booking.setLicenseNumber(request.getLicenseNumber());
        booking.setAadharDocumentPath(fileStorageService.store(request.getAadharDocument(), "aadhar"));
        booking.setPanDocumentPath(fileStorageService.store(request.getPanDocument(), "pan"));
        booking.setLicenseDocumentPath(fileStorageService.store(request.getLicenseDocument(), "license"));
        booking.setStatus("CONFIRMED");
        booking.setTotalAmount(car.getPricePerDay().multiply(new BigDecimal(days)));
        return BookingResponse.from(bookingRepository.save(booking));
    }

    public List<BookingResponse> getUserBookings(HttpSession session) {
        User user = authService.getAuthenticatedUser(session);
        return bookingRepository.findByUserOrderByCreatedAtDesc(user)
                .stream()
                .map(BookingResponse::from)
                .collect(Collectors.toList());
    }

    public List<BookingResponse> getAllBookingsForAdmin(HttpSession session) {
        User user = authService.getAuthenticatedUser(session);
        if (user.getRole() != Role.ADMIN) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Admin access required");
        }
        return bookingRepository.findAllByOrderByCreatedAtDesc()
                .stream()
                .map(BookingResponse::from)
                .collect(Collectors.toList());
    }

    private void validateRequest(BookingRequest request) {
        if (request.getCarId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Car selection is required");
        }
        if (isBlank(request.getPickupDate()) || isBlank(request.getReturnDate())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Pickup and return dates are required");
        }
        if (isBlank(request.getPickupLocation()) || isBlank(request.getDropoffLocation())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Pickup and dropoff locations are required");
        }
        if (isBlank(request.getContactNumber()) || !request.getContactNumber().matches("[0-9]{10}")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Enter a valid 10 digit contact number");
        }
        if (isBlank(request.getEmail()) || !request.getEmail().matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Enter a valid email address");
        }
        if (isBlank(request.getAddress())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Address is required");
        }
        if (isBlank(request.getAadharNumber()) || !request.getAadharNumber().matches("[0-9]{12}")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Aadhar number must be 12 digits");
        }
        if (isBlank(request.getPanNumber()) || !request.getPanNumber().matches("[A-Z]{5}[0-9]{4}[A-Z]")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "PAN number must follow the standard format");
        }
        if (isBlank(request.getLicenseNumber()) || request.getLicenseNumber().length() < 8) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Driving licence number is required");
        }
    }

    private boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }
}
