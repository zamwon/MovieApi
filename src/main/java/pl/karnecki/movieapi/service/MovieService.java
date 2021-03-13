package pl.karnecki.movieapi.service;

import pl.karnecki.movieapi.model.Movie;


import java.util.List;
import java.util.Optional;

public interface MovieService {

    List<Movie> getAllMovies();

    Optional<Movie> getMovieById(Long id);

    Optional<Movie> getMovieByTitle(String title);

    List<Movie> getMoviesByGenre(String genre);

    List<Movie> getMoviesByProductionYear(int year);

    boolean addMovie(Movie movie);

    boolean modifyMovie(Movie movie);
}
