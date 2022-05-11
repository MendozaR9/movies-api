package com.codeup.fortran_movies_api.data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name= "genres")
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    @ManyToMany
    @JoinTable(name="movie_genre",
    joinColumns =
            @JoinColumn(name ="genre_id", referencedColumnName = "id"),
    inverseJoinColumns =
            @JoinColumn(name = "movie_id", referencedColumnName = "id")
    )
    private List<Movie> movies;
    public Genre(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Genre() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Genre{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
