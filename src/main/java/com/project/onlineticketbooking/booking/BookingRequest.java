package com.project.onlineticketbooking.booking;

public class BookingRequest {
    private final String userId;
    private final long movieId;

    public BookingRequest(String userId, long movieId) {
        this.userId = userId;
        this.movieId = movieId;
    }

    public String getUserId() {
        return userId;
    }

    public long getMovieId() {
        return movieId;
    }
}
