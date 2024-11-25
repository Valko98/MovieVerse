package org.example.movieverse.controller;

import org.example.movieverse.service.MovieService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/movies")
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public String getAllMovies(Model model) {
        model.addAttribute("movies", movieService.getAllMovies());

        return "movie/list";
    }

    @GetMapping("/{id}")
    public String getMovieById(@PathVariable Long id, Model model) {
        model.addAttribute("movie", movieService.getMovieById(id));

        return "form";
    }
}
