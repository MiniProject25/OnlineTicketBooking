package com.project.onlineticketbooking.movie;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/movie")
public class MovieController {
    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @PostMapping("/create")
    public MovieResponse create(@RequestBody MovieRequest movieRequest) {
        return movieService.addMovie(movieRequest);
    }
    
    @GetMapping("/data")
    public Optional<List<MovieResponse>> viewAll() {
        return movieService.viewAllMovies();
    }

    @PutMapping("/update/{id}")
    public MovieResponse update(@PathVariable long id, @RequestBody MovieRequest movieRequest) {
        return movieService.updateMovie(id, movieRequest);
    }

}

