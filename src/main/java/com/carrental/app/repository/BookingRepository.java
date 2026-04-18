package com.carrental.app.repository;

import com.carrental.app.model.Booking;
import com.carrental.app.model.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByUserOrderByCreatedAtDesc(User user);
    List<Booking> findAllByOrderByCreatedAtDesc();
    boolean existsByCarIdAndPickupDateLessThanEqualAndReturnDateGreaterThanEqual(Long carId, java.time.LocalDate returnDate, java.time.LocalDate pickupDate);
    boolean existsByCarId(Long carId);
}
