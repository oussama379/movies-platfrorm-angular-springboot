package com.miola.movie_reviews_website_spring_boot.exceptions;

public class MovieNotFoundException extends RuntimeException {

    public MovieNotFoundException(String message) {
        super(message);
    }

}
