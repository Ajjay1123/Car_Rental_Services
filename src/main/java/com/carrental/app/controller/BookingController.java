package com.carrental.app.controller;

import com.carrental.app.dto.BookingRequest;
import com.carrental.app.dto.BookingResponse;
import com.carrental.app.service.BookingService;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {
    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping
    public BookingResponse createBooking(@ModelAttribute BookingRequest request, HttpSession session) {
        return bookingService.createBooking(request, session);
    }

    @GetMapping("/my")
    public List<BookingResponse> myBookings(HttpSession session) {
        return bookingService.getUserBookings(session);
    }
}
