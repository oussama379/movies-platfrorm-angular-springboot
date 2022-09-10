
package com.miola.movie_reviews_website_spring_boot.services;

import com.miola.movie_reviews_website_spring_boot.dtos.MovieDto;
import com.miola.movie_reviews_website_spring_boot.dtos.UserDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserService {
    UserDto createUser(UserDto userDto);
    UserDto updateUser(UserDto userDto);
    UserDto fetchUser(Long id_user);
    Page<UserDto> fetchAllUsers(int page, int size);
    Page<UserDto> fetchUserByUsername(int page, int size, String username);
    void deleteUser(Long id_user);
    UserDto authenticate(String username, String password);
    void markOrUnmarkAsWatched(Long movieId, Long userId, boolean mark);
    void addOrRemoveFromWishList(Long movieId, Long userId, boolean add);
    void addOrRemoveFromFavoriteList(Long movieId, Long userId, boolean favorite);
    List<MovieDto> fetchWatchedList(Long userId);
    List<MovieDto> fetchWishList(Long userId);
    List<MovieDto> fetchFavoriteList(Long userId);




}
