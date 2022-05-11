package com.codeup.fortran_movies_api.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MoviesRepository extends JpaRepository<Movie , Integer> {
  List <Movie> findByTitle(String title);

@Query(nativeQuery = true, value = "select * from movies m\n" +
        "where m.year > ? and m.year <=?;")
  List<Movie>findByYearRange(String startYear, String endYear);

//@Query(nativeQuery = true, value = "select * from movies where director_id = ?;")
//List<Movie>findByDirectorId(int id);
}
