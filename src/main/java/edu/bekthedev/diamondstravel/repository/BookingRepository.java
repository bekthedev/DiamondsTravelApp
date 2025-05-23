package edu.bekthedev.diamondstravel.repository;

import edu.bekthedev.diamondstravel.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

//database
public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByUsername(String username);
}