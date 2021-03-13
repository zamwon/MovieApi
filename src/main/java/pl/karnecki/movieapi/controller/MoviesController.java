package pl.karnecki.movieapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.karnecki.movieapi.model.Movie;
import pl.karnecki.movieapi.service.MailService;
import pl.karnecki.movieapi.service.MovieServiceImpl;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/moviesApi")
public class MoviesController {


    MovieServiceImpl movieService;
    MailService mailService;

    @Autowired
    public MoviesController(MovieServiceImpl movieService, MailService mailService) {
        this.movieService = movieService;
        this.mailService = mailService;
    }

    @GetMapping(produces = {
            MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<Movie>> getAllMovies() {
        return new ResponseEntity<>(movieService.getAllMovies(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Movie>> getMovieById(@PathVariable Long id) {
        Optional<Movie> first = movieService.getMovieById(id);
        if (first.isPresent()) {
            return new ResponseEntity<>(first, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/genre/{genre}")
    public ResponseEntity<List<Movie>> getMovieByGenre(@PathVariable String genre) {

        List<Movie> first = movieService.getMoviesByGenre(genre);
        if (!first.isEmpty()) {
            return new ResponseEntity<>(first, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/title/{title}")
    public ResponseEntity<Optional<Movie>> getMovieByTitle(@PathVariable String title) {

        Optional<Movie> first = movieService.getMovieByTitle(title);
        if (first.isPresent()) {
            return new ResponseEntity<>(first, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @GetMapping("/year/{year}")
    public ResponseEntity<List<Movie>> getMovieByYear(@PathVariable int year) {

        List<Movie> first = movieService.getMoviesByProductionYear(year);
        if (!first.isEmpty()) {
            return new ResponseEntity<>(first, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @PostMapping
    public ResponseEntity<HttpStatus> addMovie(@Validated @RequestBody Movie movie) {
        boolean isAdded = movieService.addMovie(movie);
        if (isAdded) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping()
    public ResponseEntity<HttpStatus> modifyMovie(@RequestBody Movie movie) {
        if (movie.getId() != null) {
            movieService.modifyMovie(movie);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<HttpStatus> modifyMovieField(@PathVariable Long id, @RequestBody Movie movie) {
        Optional<Movie> oldMovie = movieService.getMovieById(id);
        if (oldMovie.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Movie movieToUpdate = oldMovie.get();

        if (movie.getProductionYear() != null) {
            movieToUpdate.setProductionYear(movie.getProductionYear());
        }
        if (movie.getGenre() != null) {
            movieToUpdate.setGenre(movie.getGenre());
        }
        if (movie.getTitle() != null) {
            movieToUpdate.setTitle(movie.getTitle());
        }
        if (movie.getDirector() != null) {
            movieToUpdate.setDirector(movie.getDirector());
        }
        movieService.modifyMovie(movieToUpdate);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
