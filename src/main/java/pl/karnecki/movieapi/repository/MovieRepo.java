package pl.karnecki.movieapi.repository;

import org.springframework.stereotype.Repository;
import pl.karnecki.movieapi.model.Movie;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class MovieRepo {

    List<Movie> movieList;

    public MovieRepo() {
        this.movieList = new ArrayList<>();
        movieList.add
                (new Movie(1L,
                        "Avatar",
                        "James Cameron",
                        "Scifi",
                        LocalDate.of(2009, 12, 10)));
        movieList.add
                (new Movie(2L,
                        "Zapach Kobiety",
                        "Martin Brest",
                        "Dramat",
                        LocalDate.of(1992, 12, 23)));
        movieList.add
                (new Movie(3L,
                        "Matrix",
                        "Lilly Wachowski & Lana Wachowski",
                        "Scifi",
                        LocalDate.of(1999, 3, 24)));
        movieList.add
                (new Movie(4L,
                        "Kosmiczny mecz",
                        "Joe Pytka",
                        "Komedia",
                        LocalDate.of(1996, 11, 10)));
    }
    public List<Movie> getCarList() {
        return movieList;
    }

    public boolean addMovieToList(Movie movie) {
        Optional<Movie> carOptional = movieList.stream()
                .filter(oldCar -> oldCar.getId().equals(movie.getId()))
                .findFirst();

        if (carOptional.isPresent())
            return false;

        return movieList.add(movie);
    }

    public boolean removeMovieFromList(Long id) {
        Optional<Movie> carOptional = movieList.stream()
                .filter(oldCar -> oldCar.getId().equals(id))
                .findFirst();

        if (carOptional.isPresent()) {
            movieList.remove(id.intValue());
            return true;
        }
        return false;
    }

    public boolean modify(Movie car) {
        Optional<Movie> carOptional = movieList.stream()
                .filter(oldCar -> oldCar.getId().equals(car.getId()))
                .findFirst();
        if (carOptional.isPresent()) {
            movieList.remove(carOptional.get());
            return movieList.add(car);
        }
        return false;
    }


}
