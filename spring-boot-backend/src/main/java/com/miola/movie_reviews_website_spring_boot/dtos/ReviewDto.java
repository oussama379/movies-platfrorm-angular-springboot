package com.miola.movie_reviews_website_spring_boot.dtos;

import lombok.Data;

import java.util.Date;

@Data
public class ReviewDto {
    private Long id;
    private String reviewText;
    private double rating;
    private String reviewHeadLine;
    private Date reviewDate;
    private MovieDto movieDto;
    private UserDto userDto;
}
