package com.carrental.app.controller;

import com.carrental.app.dto.BookingResponse;
import com.carrental.app.dto.CarRequest;
import com.carrental.app.model.Car;
import com.carrental.app.service.BookingService;
import com.carrental.app.service.CarAdminService;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    private final BookingService bookingService;
    private final CarAdminService carAdminService;

    public AdminController(BookingService bookingService, CarAdminService carAdminService) {
        this.bookingService = bookingService;
        this.carAdminService = carAdminService;
    }

    @GetMapping("/dashboard")
    public List<BookingResponse> dashboard(HttpSession session) {
        return bookingService.getAllBookingsForAdmin(session);
    }

    @PostMapping("/cars")
    @ResponseStatus(HttpStatus.CREATED)
    public Car createCar(@RequestBody CarRequest request, HttpSession session) {
        return carAdminService.createCar(request, session);
    }

    @PutMapping("/cars/{carId}")
    public Car updateCar(@PathVariable Long carId, @RequestBody CarRequest request, HttpSession session) {
        return carAdminService.updateCar(carId, request, session);
    }

    @DeleteMapping("/cars/{carId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCar(@PathVariable Long carId, HttpSession session) {
        carAdminService.deleteCar(carId, session);
    }
}
