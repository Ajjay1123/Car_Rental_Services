package com.carrental.app.repository;

import com.carrental.app.model.Car;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Long> {
    List<Car> findAllByOrderByIdAsc();
}
