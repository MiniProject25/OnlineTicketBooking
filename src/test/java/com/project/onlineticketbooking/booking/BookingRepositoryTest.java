package com.project.onlineticketbooking.booking;

import com.project.onlineticketbooking.movie.Movie;
import com.project.onlineticketbooking.movie.MovieRepository;
import com.project.onlineticketbooking.user.User;
import com.project.onlineticketbooking.user.UserRepository;
import com.project.onlineticketbooking.user.UserRole;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class BookingRepositoryTest {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Test
    void findByEmail_ShouldReturnBooking() {
        User user = new User("a@gmail.com", "abcd", "gyat", UserRole.USER);
        Movie movie = new Movie("YAT", "ASS");
        movieRepository.save(movie);
        userRepository.save(user);

        Booking booking = new Booking(user, movie);
        bookingRepository.save(booking);

        List<Booking> bookings = bookingRepository.findByUserEmail("a@gmail.com");
        assertEquals(1, bookings.size());

    }
}
