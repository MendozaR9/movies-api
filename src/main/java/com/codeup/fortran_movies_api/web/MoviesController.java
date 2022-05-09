package com.codeup.fortran_movies_api.web;

import com.codeup.fortran_movies_api.data.Movie;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin //this is to help with local dev testing
 @RestController
@RequestMapping(value = "/api/movies")
public class MoviesController {

    private  List<Movie>  sampleMovies = setMovie();

    @GetMapping
        public Movie  one() {
//        return new Movie();
        return sampleMovies.get(1);
    }

    @GetMapping("all") // path becomes: /api/movies/all
    public List<Movie> getAll(){
//        return new ArrayList<>();
        return sampleMovies;
    }
   @GetMapping("{id}")
    public  Movie getById(@PathVariable int id){
        return sampleMovies.stream().filter((movie)->{
            return movie.getId() == id;
        })
                .findFirst()
                .orElse(null);
   }



    private  List<Movie>setMovie(){
        List<Movie> movie = new ArrayList<>();
        movie.add(new Movie(1,"Test Movie", "2024","Test director", "actor 1, actor 2", " 10", "something","Action",
                "Insert something about the plot."));
        movie.add(new Movie(2, "The Big Lebowski",
                "1995", "The Cohen Bros",
                "Jeff Bridges, John Goodman, Steve Buscemi",
                "idk bro", "idk","comedy, drama?",
                "the dude just wanted to relax and go bowling"));
        return movie;
    }

}


