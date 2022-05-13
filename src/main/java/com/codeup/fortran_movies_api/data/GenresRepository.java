package com.codeup.fortran_movies_api.data;

import org.springframework.data.jpa.repository.JpaRepository;

public interface GenresRepository extends JpaRepository <Genre, Integer >{
    Genre findGenreByName(String genre);
}
