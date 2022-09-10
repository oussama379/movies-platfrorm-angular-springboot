package com.miola.movie_reviews_website_spring_boot.exceptions;

public class ReviewNotFoundException extends RuntimeException {

    public ReviewNotFoundException(String message) {
        super(message);
    }

}
