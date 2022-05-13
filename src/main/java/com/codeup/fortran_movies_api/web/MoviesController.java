package com.codeup.fortran_movies_api.web;

import com.codeup.fortran_movies_api.data.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin //this is to help with local dev testing
 @RestController
@RequestMapping(value = "/api/movies")
public class MoviesController {

//    private  List<Movie>  sampleMovies = setMovie();

    private final MoviesRepository moviesRepository;
    private  final DirectorsRepository directorsRepository;
    private final GenresRepository genresRepository;
    private final ActorRepository actorRepository;

    public  MoviesController(MoviesRepository moviesRepository, DirectorsRepository directorsRepository, GenresRepository genresRepository, ActorRepository actorRepository){
        this.moviesRepository=moviesRepository;
        this.directorsRepository = directorsRepository;
        this.genresRepository = genresRepository;
        this.actorRepository = actorRepository;
    }

    @GetMapping("all") // path becomes: /api/movies/all
    public List<MovieDto> getAll(){
        List<Movie> movieEntitles = moviesRepository.findAll();
        List<MovieDto> movieDto = new ArrayList<>();
        for (Movie movie: movieEntitles) {
            movieDto.add(new MovieDto(movie.getId(),
                    movie.getTitle(),
                    movie.getRating(),
                    movie.getPoster(),
                    movie.getYear(),
                    movie.getGenres().stream().map(Genre::getName).collect(Collectors.joining(", ")),
                    movie.getDirector().getName(),
                    movie.getPlot(),
                    movie.getActors().stream().map(Actor::getName).collect(Collectors.joining(", "))
                    ));
        }
        return movieDto;
    }

   @GetMapping("{id}")
    public  Movie getById(@PathVariable int id){
        return moviesRepository.findById(id).orElse(null);
   }

   @GetMapping("Search/title")
   public List<Movie> getByTitle(@RequestParam String title){
       return moviesRepository.findByTitle(title);
   }

   @GetMapping("search/year")
   public List<Movie> getByYearRange(@RequestParam ("StartYear") String startYear, @RequestParam("endYear") String endYear){
    return moviesRepository.findByYearRange(startYear, endYear);
   }

   @GetMapping("search/director")
   public List<Director> getByDirector(@RequestParam("name") String directorName){
//       try {  System.out.println(directorName);
//     int directorsId =  directorsRepository.findByName(directorName).get(0).getId();
//
//         return moviesRepository.findByDirectorId(directorsId);
//     }catch (Exception ex){
//         throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"There is no Director with the name : "+ directorName);
//     }
       List<Director> directors = directorsRepository.findByName(directorName);
       return directors;
   }


        @PostMapping
        public void create(@RequestBody MovieDto movieDto){
            System.out.println(movieDto);
            Movie movieToAdd =new Movie(
                    movieDto.getTitle(),
                    movieDto.getYear(),
                    movieDto.getPoster(),
                    movieDto.getRating(),
                    movieDto.getPlot()
            );

            List<Director> directorInDb = directorsRepository.findByName(movieDto.getDirector());
            System.out.println(directorInDb);
            if (directorInDb.isEmpty()){
                Director newDirector = new Director(movieDto.getDirector());
                movieToAdd.setDirector(directorsRepository.save(newDirector));
            }else {
                movieToAdd.setDirector(directorInDb.get(0));
            }

            String [] genres = movieDto.getGenre().split(", ");
            List<Genre> movieGenres = new ArrayList<>();
            for (String genre :genres) {
                Genre genreInDb= genresRepository.findGenreByName(genre);
                System.out.println(genreInDb);
                if (genreInDb == null){
                    Genre newGenre = new Genre(genre);
                   movieGenres.add(genresRepository.save(newGenre));
                }else {
                    movieGenres.add(genreInDb);
                }
            }
            movieToAdd.setGenres(movieGenres);

            String [] actors = movieDto.getActors().split(", ");
            List<Actor> movieActors = new ArrayList<>();
            for (String actor : actors){
                Actor actorInDb = actorRepository.findActorByName(actor);
                System.out.println(actorInDb);
                if (actorInDb==null){
                    Actor newActor = new Actor(actor);
                    movieActors.add(actorRepository.save(newActor));
                }else {
                    movieActors.add(actorInDb);
                }
                movieToAdd.setActors(movieActors);
            }
            moviesRepository.save(movieToAdd);
    }

    @PostMapping("all")
    public void createAll(@RequestBody List<Movie> movies){
        System.out.println(movies);
        moviesRepository.saveAll(movies);
    }

    @DeleteMapping("{id}") //api/movies/{id} -> Delete
    public  void deleteById(@PathVariable int id ){
        try {
            moviesRepository.deleteById(id);
        }catch (Exception ex){
           throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "NO matching movie with the id: "+id);
        }
    }


//    private  List<Movie>setMovie() {
//        List<Movie> movie = new ArrayList<>();
//        movie.add(new Movie(1, "Test Movie", "2024", "Test director", "actor 1, actor 2", "Action",
//                "Insert something about the plot."));
//        movie.add(new Movie(2, "The Big Lebowski",
//                "1995", "The Cohen Bros",
//                "Jeff Bridges, John Goodman, Steve Buscemi", "comedy, drama?",
//                "the dude just wanted to relax and go bowling"));
//        return movie;
//    }
}


