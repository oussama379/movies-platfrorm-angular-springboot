package com.miola.movie_reviews_website_spring_boot.exceptions;

public class UsernameNotUniqueException extends RuntimeException {

    public UsernameNotUniqueException(String message) {
        super(message);
    }

}
