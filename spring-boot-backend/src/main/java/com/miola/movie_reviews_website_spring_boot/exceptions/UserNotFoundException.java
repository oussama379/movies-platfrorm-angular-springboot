package com.miola.movie_reviews_website_spring_boot.exceptions;

import com.miola.movie_reviews_website_spring_boot.dtos.UserDto;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String message) {
        super(message);
    }

}
