package pl.karnecki.movieapi.service;

import pl.karnecki.movieapi.model.Movie;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface MovieService {

    List<Movie> getAllMovies();

    Optional<Movie> getMovieById(Long id);

    Optional<Movie> getMovieByTitle(String title);

    List<Movie> getMoviesByGenre(String genre);

    List<Movie> getMoviesByProductionYear(LocalDate localDate);

    boolean addMovie(Movie movie);

    boolean deleteMovie(Long id);

    boolean modifyMovie(Movie movie);
}
