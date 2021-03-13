package pl.karnecki.movieapi.model;

import java.time.LocalDate;
import java.util.Objects;

public class Movie {

    Long id;
    String title;
    String director;
    String genre;
    LocalDate productionYear;

    public Movie(Long id, String title, String director, String genre, LocalDate productionYear) {
        this.id = id;
        this.title = title;
        this.director = director;
        this.genre = genre;
        this.productionYear = productionYear;
    }

    public Movie() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public LocalDate getProductionYear() {
        return productionYear;
    }

    public void setProductionYear(LocalDate productionYear) {
        this.productionYear = productionYear;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return Objects.equals(id, movie.id) && Objects.equals(title, movie.title) && Objects.equals(director, movie.director) && Objects.equals(genre, movie.genre) && Objects.equals(productionYear, movie.productionYear);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, director, genre, productionYear);
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", director='" + director + '\'' +
                ", genre='" + genre + '\'' +
                ", productionYear=" + productionYear +
                '}';
    }
}
