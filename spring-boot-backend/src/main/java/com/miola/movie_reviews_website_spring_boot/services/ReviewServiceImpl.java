package com.miola.movie_reviews_website_spring_boot.services;

import com.miola.movie_reviews_website_spring_boot.dtos.ReviewDto;
import com.miola.movie_reviews_website_spring_boot.entities.MovieEntity;
import com.miola.movie_reviews_website_spring_boot.entities.ReviewEntity;
import com.miola.movie_reviews_website_spring_boot.entities.UserEntity;
import com.miola.movie_reviews_website_spring_boot.exceptions.MovieNotFoundException;
import com.miola.movie_reviews_website_spring_boot.exceptions.ReviewNotFoundException;
import com.miola.movie_reviews_website_spring_boot.exceptions.UserNotFoundException;
import com.miola.movie_reviews_website_spring_boot.mappers.DtoMapper;
import com.miola.movie_reviews_website_spring_boot.repos.MovieRepository;
import com.miola.movie_reviews_website_spring_boot.repos.ReviewRepository;
import com.miola.movie_reviews_website_spring_boot.repos.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional
public class ReviewServiceImpl implements ReviewService{

    private UserRepository userRepository;
    private MovieRepository movieRepository;
    private ReviewRepository reviewRepository;
    private DtoMapper dtoMapper;

    @Override
    public ReviewDto createReview(ReviewDto reviewDto) {
        ReviewEntity reviewEntity = dtoMapper.fromReviewDto(reviewDto);
        MovieEntity movie = movieRepository.findById(reviewEntity.getMovie().getMovieId()).orElse(null);
        if(movie == null) {
            movieRepository.save(new MovieEntity(reviewEntity.getMovie().getMovieId()));
            movie = movieRepository.findById(reviewEntity.getMovie().getMovieId()).orElse(null);
            if(movie != null) reviewEntity.setMovie(movie);
            else throw new MovieNotFoundException("Movie Not Found");
        }
            UserEntity user = userRepository.findById(reviewEntity.getUser().getId()).orElse(null);
            if(user == null) throw new UserNotFoundException("User Not Found");
        reviewRepository.save(reviewEntity);
        return reviewDto;
    }

    @Override
    public ReviewDto editeReview(ReviewDto reviewDto) {
        ReviewEntity reviewEntity = reviewRepository.findById(reviewDto.getId()).orElse(null);
        if(reviewEntity == null) throw new ReviewNotFoundException("Review Not Found");
        reviewEntity = dtoMapper.fromReviewDto(reviewDto);
        reviewRepository.save(reviewEntity);
        return reviewDto;
    }

    @Override
    public void deleteReview(ReviewDto reviewDto) {
        if(reviewRepository.existsById(reviewDto.getId())) reviewRepository.deleteById(reviewDto.getId());
        else throw new ReviewNotFoundException("Review Not Found");
    }

    @Override
    public List<ReviewDto>  getAllUserReviews(Long userId) {
        UserEntity user = userRepository.findById(userId).orElse(null);
        if(user != null) return reviewRepository.findByUser(user).stream().map(dtoMapper::fromReview).collect(Collectors.toList());
        else throw new UserNotFoundException("User Not Found");
    }

    @Override
    public List<ReviewDto> getReviewsByMovie(Long movieId) {
        MovieEntity movie = movieRepository.findById(movieId).orElse(null);
        if(movie != null) return reviewRepository.findByMovie(movie).stream().map(dtoMapper::fromReview).collect(Collectors.toList());
        else throw new MovieNotFoundException("Movie Not Found");
    }
}
