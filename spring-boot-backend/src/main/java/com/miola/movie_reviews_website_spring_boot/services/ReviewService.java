package com.miola.movie_reviews_website_spring_boot.services;

import com.miola.movie_reviews_website_spring_boot.dtos.ReviewDto;

import java.util.List;

public interface ReviewService {
    ReviewDto createReview(ReviewDto reviewDto);
    ReviewDto editeReview(ReviewDto reviewDto);
    void deleteReview(ReviewDto reviewDto);
    List<ReviewDto> getAllUserReviews(Long userId);
    List<ReviewDto> getReviewsByMovie(Long movieId);

}
