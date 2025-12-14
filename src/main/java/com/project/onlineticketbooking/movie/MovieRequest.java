package com.project.onlineticketbooking.movie;

public class MovieRequest {
    private final String movieName;
    private final String movieGenre;

    public MovieRequest(String movieName, String movieGenre) {
        this.movieName = movieName;
        this.movieGenre = movieGenre;
    }

    public String getMovieName() {
        return movieName;
    }

    public String getMovieGenre() {
        return movieGenre;
    }
}
