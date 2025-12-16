package com.project.onlineticketbooking.booking;

import com.project.onlineticketbooking.movie.Movie;
import com.project.onlineticketbooking.user.User;

public class BookingResponse {
    private final long id;
    private final Movie movieId;
    private final User userId;

    public BookingResponse(long id, Movie movieId, User userId) {
        this.id = id;
        this.movieId = movieId;
        this.userId = userId;
    }

    public long getId() {
        return id;
    }

    public Movie getMovieId() {
        return movieId;
    }

    public User getUserId() {
        return userId;
    }

    public static BookingResponse from(Booking booking) {
        return new BookingResponse(booking.getBookingId(), booking.getBookedMovie(), booking.getBookedUser());
    }
}
