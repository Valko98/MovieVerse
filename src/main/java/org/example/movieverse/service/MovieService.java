package org.example.movieverse.service;

import jakarta.persistence.EntityNotFoundException;
import org.example.movieverse.dto.MovieDTO;
import org.example.movieverse.model.Actor;
import org.example.movieverse.model.Movie;
import org.example.movieverse.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieService {

    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<MovieDTO> getAllMovies() {
        List<Movie> movies = movieRepository.findAll();

        return movies.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public MovieDTO getMovieById(Long id) {
        return movieRepository.findById(id).map(this::convertToDTO)
                .orElseThrow(() -> new EntityNotFoundException("Movie not found"));
    }

    private MovieDTO convertToDTO(Movie movie) {
        MovieDTO dto = new MovieDTO();
        dto.setId(movie.getId());
        dto.setTitle(movie.getTitle());
        dto.setGenre(movie.getGenre());
        dto.setDescription(movie.getDescription());
        dto.setReleaseYear(movie.getReleaseYear());
        dto.setDirectorName(movie.getDirector().getName());
        dto.setActorNames(movie.getActors().stream().map(Actor::getName).collect(Collectors.toList()));

        return dto;
    }
}
