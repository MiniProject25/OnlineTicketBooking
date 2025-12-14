package com.project.onlineticketbooking.movie;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MovieService {

    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public MovieResponse addMovie(MovieRequest movie) {
        Movie newMovie = new Movie(movie.getMovieName(), movie.getMovieGenre());
        movieRepository.save(newMovie);
        return MovieResponse.from(newMovie);
    }

    public Optional<List<MovieResponse>> viewAllMovies() {
        return Optional.of(movieRepository.findAll().stream().map(MovieResponse::from).collect(Collectors.toList()));
    }

    public MovieResponse updateMovie(long id, MovieRequest movieRequest) {
        Movie existing = movieRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Movie not found with id " + id));

        if (movieRequest.getMovieName() != null)
            existing.setName(movieRequest.getMovieName());
        if (movieRequest.getMovieGenre() != null)
            existing.setGenre(movieRequest.getMovieGenre());

        movieRepository.save(existing);

        return MovieResponse.from(existing);
    }
}
