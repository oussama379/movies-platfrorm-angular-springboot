package com.miola.movie_reviews_website_spring_boot.dtos;

import lombok.Data;
import lombok.ToString;

@Data
public class UserDto {
    private Long id;
    private String fullname;
    private String username;
    private String email;
    private String password;
}
