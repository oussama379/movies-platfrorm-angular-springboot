package com.miola.movie_reviews_website_spring_boot.mappers;


import com.fasterxml.jackson.databind.util.BeanUtil;
import com.miola.movie_reviews_website_spring_boot.dtos.MovieDto;
import com.miola.movie_reviews_website_spring_boot.dtos.ReviewDto;
import com.miola.movie_reviews_website_spring_boot.dtos.UserDto;
import com.miola.movie_reviews_website_spring_boot.entities.MovieEntity;
import com.miola.movie_reviews_website_spring_boot.entities.ReviewEntity;
import com.miola.movie_reviews_website_spring_boot.entities.UserEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class DtoMapper {

    public UserDto fromUser(UserEntity user){
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(user, userDto);
        return userDto;
    }

    public UserEntity fromUserDto(UserDto userDto){
        UserEntity user = new UserEntity();
        BeanUtils.copyProperties(userDto, user);
        return user;
    }

    public MovieDto fromMovie(MovieEntity movie){
        MovieDto movieDto = new MovieDto();
        BeanUtils.copyProperties(movie, movieDto);
        return movieDto;
    }

    public MovieEntity fromMovieDto(MovieDto movieDto){
        MovieEntity movie = new MovieEntity();
        BeanUtils.copyProperties(movie, movieDto);
        return movie;
    }

    public ReviewDto fromReview(ReviewEntity review){
        ReviewDto reviewDto = new ReviewDto();
        BeanUtils.copyProperties(review, reviewDto);
        reviewDto.setMovieDto(fromMovie(review.getMovie()));
        reviewDto.setUserDto(fromUser(review.getUser()));
        return reviewDto;
    }

    public ReviewEntity fromReviewDto(ReviewDto reviewDto){
        ReviewEntity review = new ReviewEntity();
        BeanUtils.copyProperties(review, reviewDto);
        review.setMovie(fromMovieDto(reviewDto.getMovieDto()));
        review.setUser(fromUserDto(reviewDto.getUserDto()));
        return review;
    }


}
