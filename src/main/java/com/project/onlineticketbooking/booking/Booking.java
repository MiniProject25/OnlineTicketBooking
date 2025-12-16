package com.project.onlineticketbooking.booking;

import com.project.onlineticketbooking.movie.Movie;
import com.project.onlineticketbooking.user.User;
import jakarta.persistence.*;

@Entity
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long bookingId;

    @ManyToOne
    @JoinColumn(name="user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name="movie_id", nullable = false)
    private Movie movie;

    protected Booking() {}

    public Booking(User user, Movie movie) {
        this.user = user;
        this.movie = movie;
    }

    public long getBookingId() {
        return bookingId;
    }

    public User getBookedUser() {
        return user;
    }

    public Movie getBookedMovie() {
        return movie;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }
}
