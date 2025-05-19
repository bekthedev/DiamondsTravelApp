package edu.bekthedev.diamondstravel.service;

import edu.bekthedev.diamondstravel.model.Booking;
import edu.bekthedev.diamondstravel.repository.BookingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;

    //constructor
    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }
    //saave booking
    public Booking saveBooking(Booking booking) {
        return bookingRepository.save(booking);
    }
    //get bookings for user
    public List<Booking> getBookingsForUser(String username) {
        return bookingRepository.findByUsername(username);
    }
}