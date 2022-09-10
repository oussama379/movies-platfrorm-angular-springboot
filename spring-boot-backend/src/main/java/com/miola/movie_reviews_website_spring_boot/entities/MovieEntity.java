package com.miola.movie_reviews_website_spring_boot.entities;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@ToString
@NoArgsConstructor
public class MovieEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long movieId;
    private String name;
    private String description;
    @OneToMany(mappedBy = "movie")
    private Set<ReviewEntity> reviewSet;
    @OneToMany(mappedBy = "movie")
    private Set<Watched> watchedList;
    @OneToMany(mappedBy = "movie")
    private Set<Favorites> favorites;
    @OneToMany(mappedBy = "movie")
    private Set<Wishes> wishes;

    public MovieEntity(Long movieId) {
        this.movieId = movieId;
    }

    @Override
    public String toString() {
        return "MovieEntity{" +
                "movieId=" + movieId +
                ", name='" + name + '\'' +
                ", description='" + description +
                '}';
    }
}
