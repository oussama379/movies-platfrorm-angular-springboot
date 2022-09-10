package com.miola.movie_reviews_website_spring_boot.dtos;

import lombok.Data;

@Data
public class MovieDto {
    private Long movieId;
    private String name;
    private String description;
}
