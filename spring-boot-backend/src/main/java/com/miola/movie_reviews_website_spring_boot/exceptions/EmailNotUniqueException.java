package com.miola.movie_reviews_website_spring_boot.exceptions;

public class EmailNotUniqueException extends RuntimeException {

    public EmailNotUniqueException(String message) {
        super(message);
    }

}
