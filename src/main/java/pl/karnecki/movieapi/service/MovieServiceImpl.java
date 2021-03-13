package pl.karnecki.movieapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.karnecki.movieapi.model.Movie;
import pl.karnecki.movieapi.repository.MovieRepo;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepo movieRepo;

    @Autowired
    public MovieServiceImpl(MovieRepo movieRepo) {
        this.movieRepo = movieRepo;
    }


    @Override
    public List<Movie> getAllMovies() {
        return movieRepo.getCarList();
    }

    @Override
    public Optional<Movie> getMovieById(Long id) {
        return movieRepo.getCarList()
                .stream()
                .filter(movie -> movie.getId().equals(id))
                .findFirst();
    }

    @Override
    public Optional<Movie> getMovieByTitle(String title) {
        return movieRepo.getCarList()
                .stream()
                .filter(movie -> movie.getTitle().equals(title))
                .findFirst();
    }

    @Override
    public List<Movie> getMoviesByGenre(String genre) {
        return movieRepo.getCarList()
                .stream()
                .filter(movie -> movie.getGenre().equals(genre))
                .collect(Collectors.toList());
    }

    @Override
    public List<Movie> getMoviesByProductionYear(LocalDate localDate) {
        return movieRepo.getCarList()
                .stream()
                .filter(movie -> movie.getProductionYear().equals(localDate.getYear()))
                .collect(Collectors.toList());
    }

    @Override
    @SendMailAfterPost
    public boolean addMovie(Movie movie) {
        boolean isMovieOnList = movieRepo.getCarList()
                .stream()
                .anyMatch(movie1 ->
                        movie1.getId().equals(movie.getId()));
        if (isMovieOnList) return false;
        movieRepo.addCarToList(movie);
        return true;
    }

    @Override
    public boolean deleteMovie(Long id) {
        return movieRepo.removeMovieFromList(id);
    }

    @Override
    public boolean modifyMovie(Movie movie) {
        boolean isMovieOnList = movieRepo.getCarList()
                .stream()
                .anyMatch(movie1 ->
                        movie1.getId().equals(movie.getId()));
        if (isMovieOnList) {
            movieRepo.modify(movie);
            return true;
        } else
            return false;
    }
}
