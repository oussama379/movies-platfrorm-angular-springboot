package com.miola.movie_reviews_website_spring_boot.repos;

import com.miola.movie_reviews_website_spring_boot.entities.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface MovieRepository extends CrudRepository<MovieEntity, Long> {
}