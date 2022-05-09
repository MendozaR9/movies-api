package com.codeup.fortran_movies_api.data;

public class Movie {
    private int id;
    private String title;
    private String year;
    private String director;
    private String actors;
    private String imdbId;
    private String movieser;
    private String genre;
    private String plot;

    public Movie() {
    }

    public Movie(int id, String title, String year, String director, String actors, String imdbId, String movieser, String genre, String plot) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.director = director;
        this.actors = actors;
        this.imdbId = imdbId;
        this.movieser = movieser;
        this.genre = genre;
        this.plot = plot;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public String getImdbId() {
        return imdbId;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    public String getMovieser() {
        return movieser;
    }

    public void setMovieser(String movieser) {
        this.movieser = movieser;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", year='" + year + '\'' +
                ", director='" + director + '\'' +
                ", actors='" + actors + '\'' +
                ", imdbId='" + imdbId + '\'' +
                ", movieser='" + movieser + '\'' +
                ", genre='" + genre + '\'' +
                ", plot='" + plot + '\'' +
                '}';
    }
}
