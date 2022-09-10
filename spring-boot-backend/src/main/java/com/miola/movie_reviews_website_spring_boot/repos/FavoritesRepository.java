package com.miola.movie_reviews_website_spring_boot.repos;

import com.miola.movie_reviews_website_spring_boot.entities.Favorites;
import com.miola.movie_reviews_website_spring_boot.entities.MovieEntity;
import com.miola.movie_reviews_website_spring_boot.entities.UserEntity;
import com.miola.movie_reviews_website_spring_boot.entities.Watched;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface FavoritesRepository extends CrudRepository<Favorites, Long> {

    Favorites findByUserAndMovie(UserEntity user, MovieEntity movie);
    List<Favorites> findByUser(UserEntity user);
    boolean deleteByUser(UserEntity user);
}