package com.project.onlineticketbooking.booking;

import com.project.onlineticketbooking.exception.UserNotFoundException;
import com.project.onlineticketbooking.movie.Movie;
import com.project.onlineticketbooking.movie.MovieRepository;
import com.project.onlineticketbooking.user.User;
import com.project.onlineticketbooking.user.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class BookingService {
    private final BookingRepository bookingRepository;
    private final MovieRepository movieRepository;
    private final UserRepository userRepository;

    public BookingService(BookingRepository bookingRepository, MovieRepository movieRepository, UserRepository userRepository) {
        this.bookingRepository = bookingRepository;
        this.movieRepository = movieRepository;
        this.userRepository = userRepository;
    }

    public BookingResponse addBooking(BookingRequest booking) {
        User user = userRepository.findById(booking.getUserId()).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        Movie movie = movieRepository.findById(booking.getMovieId()).orElse(null);

        Booking newBooking = new Booking(user, movie);
        bookingRepository.save(newBooking);

        return BookingResponse.from(newBooking);
    }

    public BookingResponse cancelBooking(long bookingId) {
        Booking existing = bookingRepository.findById(bookingId).orElse(null);
        if (existing != null) {
            bookingRepository.deleteById(bookingId);
            return BookingResponse.from(existing);
        }
        return null;
    }

    public BookingResponse updateBooking(long BookingId, BookingUpdate bookingUpdate) {
        Booking existing = bookingRepository.findById(BookingId).orElse(null);

        if (existing != null) {
            User user = null;
            Movie movie = null;
            if (!bookingUpdate.getUserId().isEmpty())
                user = userRepository.findById(bookingUpdate.getUserId()).orElseThrow(() -> new UserNotFoundException("User not found"));
            if (bookingUpdate.getMovieId() != 0L)
                movie = movieRepository.findById(bookingUpdate.getMovieId()).orElse(null);

            existing.setUser(user);
            existing.setMovie(movie);

            bookingRepository.save(existing);
            return BookingResponse.from(existing);
        }

        return null;
    }

    public List<BookingResponse> viewAllBooking() {
        List<Booking> bookings = bookingRepository.findAll();
        return bookings.stream().map(BookingResponse::from).toList();
    }

    public List<BookingResponse> getAllBookings(String email) {
        return bookingRepository.findByUserEmail(email)
                .stream().map(BookingResponse::from).toList();
    }
}
