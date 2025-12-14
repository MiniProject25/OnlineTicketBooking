package com.project.onlineticketbooking.movie;

public class MovieResponse {
    private final long movieId;
    private final String genre;
    private final String name;

    public MovieResponse(long movieId, String genre, String name) {
        this.movieId = movieId;
        this.genre = genre;
        this.name = name;
    }

    public long getMovieId() {
        return movieId;
    }

    public String getGenre() {
        return genre;
    }

    public String getName() {
        return name;
    }

    public static MovieResponse from(Movie movie) {
        return new MovieResponse(movie.getId(), movie.getGenre(), movie.getName());
    }

}
