# 1. go get the json file from glitch
# 2. copypasta into a new json file under /resources

# --> You may need to establish a connection to your localhost db here

# 3. create the movies_db
create database if not exists  movies_db;

# 4. use the movies_db
use movies_db;
# 5. drop the table(s) to which no other tables are dependent (none at first)
drop table if exists movie_actor ;
drop table if exists movie_genre;
drop table if exists actors;
drop table if exists  genres;
drop table if exists movies;
drop table if exists  directors;
# 6. map the json movie properties to movies table columns
# --> start with just a movies table with all the columns found in the movie json properties

create table if not exists  directors(
     id int unsigned not null  auto_increment primary key,
     director varchar(255) not null
);
create table if not exists movies(
    id int unsigned not null  auto_increment,
    title varchar(255) not null,
    year varchar(120) not null ,
    poster varchar(255) ,
    ratting char(10) not null ,
    plot text,
    director_id int unsigned not null ,
    primary key (id),
    foreign key (director_id) references directors(id)
);
# 6a. Run the script to make sure it works
describe movies;
# 7. refactor to extract the directors to a new table with just an id and name
describe directors;
# --> change the movies table to reference the directors table via Foreign Key
# --> now that movies is dependent on directors, you need to move directors above movies in the script

# 8. Go add DROP IF EXIST statements for movies and directors

# 9. RUN IT!

#10 add a table for genre, and create a table b/t movies and genre
create table if not exists  genres(
    id int unsigned not null auto_increment,
    name varchar(32),
    primary key (id)
);

create table if not exists movie_genre(
    movie_id int unsigned not null,
    genre_id int unsigned not null,
    foreign key (movie_id) references movies(id),
    foreign key (genre_id) references  genres(id)
);

create  table if not exists actors(
    id int unsigned not null  auto_increment,
    name varchar(255),
    primary key (id)
);

create table if not exists movie_actor(
    movie_id int unsigned not null,
    actor_id int unsigned not null,
    foreign key (movie_id) references  movies(id),
    foreign key (actor_id) references actors(id)
);




