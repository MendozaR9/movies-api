package com.codeup.fortran_movies_api.data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "actors")
public class Actor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    @ManyToMany
    @JoinTable(name = "movie_actor",
    joinColumns =
    @JoinColumn(name = "actor_id", referencedColumnName = "id"),
    inverseJoinColumns =
    @JoinColumn(name = "movie_id", referencedColumnName = "id")
    )
    private List<Movie> movies;


    public Actor(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Actor() {
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
        return "Actor{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
