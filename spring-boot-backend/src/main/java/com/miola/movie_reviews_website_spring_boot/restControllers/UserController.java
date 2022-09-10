package com.miola.movie_reviews_website_spring_boot.restControllers;


import com.miola.movie_reviews_website_spring_boot.dtos.ReviewDto;
import com.miola.movie_reviews_website_spring_boot.dtos.UserDto;
import com.miola.movie_reviews_website_spring_boot.entities.ReviewEntity;
import com.miola.movie_reviews_website_spring_boot.jsonModels.Movie;
import com.miola.movie_reviews_website_spring_boot.mappers.DtoMapper;
import com.miola.movie_reviews_website_spring_boot.services.ReviewServiceImpl;
import com.miola.movie_reviews_website_spring_boot.services.TMDbServiceImpl;
import com.miola.movie_reviews_website_spring_boot.services.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
@CrossOrigin("http://localhost:4200")
@AllArgsConstructor
public class UserController {

    private UserServiceImpl userService;
    private TMDbServiceImpl tmDbService;
    private ReviewServiceImpl reviewService;
    private DtoMapper dtoMapper;

    @GetMapping("{page}/{size}")
    public Page<UserDto> getAllUsers(@PathVariable int page, @PathVariable int size){
        return userService.fetchAllUsers(page,size);
    }

    @PostMapping
    public UserDto createUser(@RequestBody UserDto user){
        return userService.createUser(user);
    }

    @PostMapping("/login")
    public UserDto login(@RequestBody UserDto user){
        return userService.authenticate(user.getUsername(), user.getPassword());
    }

    @PutMapping("/{id_user}")
    public UserDto editUser(@RequestBody UserDto user, @PathVariable Long id_user){
        user.setId(id_user);
        return userService.updateUser(user);
    }

    @DeleteMapping("/{id_user}")
    public void deleteUser(@PathVariable Long id_user){
        userService.deleteUser(id_user);
    }

    @GetMapping("/{id_user}")
    public UserDto getUser(@PathVariable Long id_user){
        return userService.fetchUser(id_user);
    }

    @GetMapping("/search/{username}/{page}/{size}")
    public Page<UserDto> getUserByUsername(@PathVariable String username, @PathVariable int page, @PathVariable int size){
        return userService.fetchUserByUsername(page, size, username);
    }

    @GetMapping("/markWatched/{id_user}/{id_movie}")
    public void markAsWatched(@PathVariable Long id_user, @PathVariable Long id_movie){
        userService.markOrUnmarkAsWatched(id_movie, id_user, true);
    }

    @GetMapping("/unMarkWatched/{id_user}/{id_movie}")
    public void unMarkAsWatched(@PathVariable Long id_user, @PathVariable Long id_movie){
        userService.markOrUnmarkAsWatched(id_movie, id_user, false);
    }

    @GetMapping("/watchedList/{id_user}")
    public List<Movie> getWatchedList(@PathVariable Long id_user){
        List<Movie> watchedListJson = new ArrayList<>();
       userService.fetchWatchedList(id_user).forEach(m -> {
            String url = "https://api.themoviedb.org/3/movie/"+m.getMovieId()+"?api_key=6c204a7d2fd848e65c5f5230dbc85bb9";
            Movie M = tmDbService.fetchMovieById(url);
            watchedListJson.add(M);
        });
        return watchedListJson;
    }

    @GetMapping("/addToWishList/{id_user}/{id_movie}")
    public void addToWishList(@PathVariable Long id_user, @PathVariable Long id_movie){
        userService.addOrRemoveFromWishList(id_movie, id_user, true);
    }
    @GetMapping("/removeFromWishList/{id_user}/{id_movie}")
    public void removeFromWishList(@PathVariable Long id_user, @PathVariable Long id_movie){
        userService.addOrRemoveFromWishList(id_movie, id_user, false);
    }

    @GetMapping("/wishList/{id_user}")
    public List<Movie> getWishList(@PathVariable Long id_user){
        List<Movie> wishListJson = new ArrayList<>();
        userService.fetchWishList(id_user).forEach(m -> {
            String url = "https://api.themoviedb.org/3/movie/"+m.getMovieId()+"?api_key=6c204a7d2fd848e65c5f5230dbc85bb9";
            Movie M = tmDbService.fetchMovieById(url);
            wishListJson.add(M);
        });
        return wishListJson;
    }
    @GetMapping("/addToFavoritesList/{id_user}/{id_movie}")
    public void addToFavoritesList(@PathVariable("id_user") Long id_user, @PathVariable Long id_movie){
        userService.addOrRemoveFromFavoriteList(id_movie, id_user, true);
    }
    @GetMapping("/removeFromFavoritesList/{id_user}/{id_movie}")
    public void removeFromFavoritesList(@PathVariable Long id_user, @PathVariable Long id_movie){
        userService.addOrRemoveFromFavoriteList(id_movie, id_user, false);
    }
    @GetMapping("/favoritesList/{id_user}")
    public List<Movie> getFavoritesList(@PathVariable Long id_user){
        List<Movie> favoritesListJson = new ArrayList<>();
        userService.fetchFavoriteList(id_user).forEach(m -> {
            String url = "https://api.themoviedb.org/3/movie/"+m.getMovieId()+"?api_key=6c204a7d2fd848e65c5f5230dbc85bb9";
            Movie M = tmDbService.fetchMovieById(url);
            favoritesListJson.add(M);
        });
        return favoritesListJson;
    }

    @PostMapping("/review")
    public ReviewDto createReview(@RequestBody ReviewDto review){
        return reviewService.createReview(review);
    }

    @PutMapping("/review/{id_review}")
    public ReviewDto editReview(@RequestBody ReviewDto review, @PathVariable Long id_review){
        review.setId(id_review);
        return reviewService.editeReview(review);
    }

    @DeleteMapping("/review")
    public void deleteReview(@RequestBody ReviewDto review){
        reviewService.deleteReview(review);
    }

    @GetMapping("/review/user/{id_user}")
    public List<ReviewDto> getReviewsByUser(@PathVariable Long id_user){
        return reviewService.getAllUserReviews(id_user);
    }

    @GetMapping("/review/movie/{id_movie}")
    public List<ReviewDto> getReviewsByMovie(@PathVariable("id_movie") Long id_movie){
        return reviewService.getReviewsByMovie(id_movie);
    }

}
