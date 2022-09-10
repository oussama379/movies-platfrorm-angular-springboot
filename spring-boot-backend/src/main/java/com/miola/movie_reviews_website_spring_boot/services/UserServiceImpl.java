package com.miola.movie_reviews_website_spring_boot.services;

import com.miola.movie_reviews_website_spring_boot.dtos.MovieDto;
import com.miola.movie_reviews_website_spring_boot.dtos.UserDto;
import com.miola.movie_reviews_website_spring_boot.entities.*;
import com.miola.movie_reviews_website_spring_boot.exceptions.EmailNotUniqueException;
import com.miola.movie_reviews_website_spring_boot.exceptions.MovieNotFoundException;
import com.miola.movie_reviews_website_spring_boot.exceptions.UserNotFoundException;
import com.miola.movie_reviews_website_spring_boot.exceptions.UsernameNotUniqueException;
import com.miola.movie_reviews_website_spring_boot.mappers.DtoMapper;
import com.miola.movie_reviews_website_spring_boot.repos.*;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional
public class UserServiceImpl implements UserService{

    private UserRepository userRepository;
    private MovieRepository movieRepository;
    private WatchedRepository watchedRepository;
    private FavoritesRepository favoritesRepository;
    private WishesRepository wishesRepository;
    private DtoMapper dtoMapper;
    private ReviewRepository reviewRepository;

    @Override
    public UserDto createUser(UserDto userDto) {
        UserEntity userEntity = dtoMapper.fromUserDto(userDto);
        if(userRepository.existsByUsername(userEntity.getUsername())) throw new UsernameNotUniqueException("This user name is already used");
        if(userRepository.existsByEmail(userEntity.getEmail())) throw new EmailNotUniqueException("This email is already used");
        String password = userEntity.getPassword();
        userEntity.setPassword(encryptionMd5(password));
        userRepository.save(userEntity);
        return userDto;

    }

    @Override
    public UserDto updateUser(UserDto UserDto) {
        if(userRepository.existsById(UserDto.getId())) {
            UserEntity userEntity = dtoMapper.fromUserDto(UserDto);
            String password = userEntity.getPassword();
            userEntity.setPassword(encryptionMd5(password));
            userRepository.save(userEntity);
            return UserDto;
        }else{
            throw new UserNotFoundException("User Not Found");
        }
    }

    @Override
    public UserDto fetchUser(Long id_user) {
        if (userRepository.existsById(id_user))
            return dtoMapper.fromUser(userRepository.findById(id_user).orElse(null));
        else
            throw new UserNotFoundException("User Not Found");
    }

    @Override
    public Page<UserDto> fetchUserByUsername(int page, int size, String username) {
        //return userRepository.searchByUsername(username).stream().map(dtoMapper::fromUser).collect(Collectors.toList());
        return userRepository.findByUsername(username, PageRequest.of(page, size)).map(dtoMapper::fromUser);
    }


    @Override
    public Page<UserDto> fetchAllUsers(int page, int size) {
        return userRepository.findAll(PageRequest.of(page, size)).map(dtoMapper::fromUser);
    }

    @Override
    public void deleteUser(Long id_user) {
        if (userRepository.existsById(id_user)) {
            userRepository.deleteById(id_user);
        }

    }

    @Override
    public UserDto authenticate(String email, String password) {
        UserEntity user = userRepository.findByEmail(email);
        if(user == null || !encryptionMd5(password).equals(user.getPassword())) throw new UserNotFoundException("User Not Found");
        return dtoMapper.fromUser(user);
    }

    @Override
    public void markOrUnmarkAsWatched(Long movieId, Long userId, boolean mark) {
        UserEntity user = userRepository.findById(userId).orElse(null);
        MovieEntity movie = movieRepository.findById(movieId).orElse(null);

        if(movie == null) movieRepository.save(new MovieEntity(movieId));
        if(user != null){
            if(mark){
                Watched watched = new Watched();
                watched.setUser(user);
                watched.setMovie(movie);
                watchedRepository.save(watched);;
            }
             if(!mark) {
                 Watched watched = watchedRepository.findByUserAndMovie(user, movie);
                 if(watched != null) watchedRepository.delete(watched);
                 else throw new MovieNotFoundException("Watched movie not found");
             }
        }else throw new UserNotFoundException("User not found");
    }

    @Override
    public void addOrRemoveFromWishList(Long movieId, Long userId, boolean add) {
        UserEntity user = userRepository.findById(userId).orElse(null);
        MovieEntity movie = movieRepository.findById(movieId).orElse(null);

        if(movie == null) movieRepository.save(new MovieEntity(movieId));
        if(user != null){
            if(add){
                Wishes wishes = new Wishes();
                wishes.setUser(user);
                wishes.setMovie(movie);
                wishesRepository.save(wishes);
            }
            if(!add) {
                Wishes wishes = wishesRepository.findByUserAndMovie(user, movie);
                if(wishes != null) wishesRepository.delete(wishes);
                else throw new MovieNotFoundException("Wished movie not found");
            }
        }else throw new UserNotFoundException("User not found");
    }

    @Override
    public void addOrRemoveFromFavoriteList(Long movieId, Long userId, boolean favorite) {
        UserEntity user = userRepository.findById(userId).orElse(null);
        MovieEntity movie = movieRepository.findById(movieId).orElse(null);

        if(movie == null) movieRepository.save(new MovieEntity(movieId));
        if(user != null){
            if(favorite){
                Favorites favorites = new Favorites();
                favorites.setUser(user);
                favorites.setMovie(movie);
                favoritesRepository.save(favorites);;
            }
            if(!favorite) {
                Favorites favorites = favoritesRepository.findByUserAndMovie(user, movie);
                if(favorites != null) favoritesRepository.delete(favorites);
                else throw new MovieNotFoundException("Favorite movie not found");
            }
        }else throw new UserNotFoundException("User not found");
    }

    @Override
    public List<MovieDto> fetchWatchedList(Long userId) {
        UserEntity user = userRepository.findById(userId).orElse(null);
        if(user != null){
            List<MovieEntity> L = new ArrayList<>();
            watchedRepository.findByUser(user).stream().forEach(w -> L.add(w.getMovie()));
            return L.stream().map(dtoMapper::fromMovie).collect(Collectors.toList());
        } else throw new UserNotFoundException("User not found");

    }

    @Override
    public List<MovieDto> fetchWishList(Long userId) {
        UserEntity user = userRepository.findById(userId).orElse(null);
        if(user != null){
            List<MovieEntity> L = new ArrayList<>();
            wishesRepository.findByUser(user).stream().forEach(w -> L.add(w.getMovie()));
            return L.stream().map(dtoMapper::fromMovie).collect(Collectors.toList());
        } else throw new UserNotFoundException("User not found");
    }

    @Override
    public List<MovieDto> fetchFavoriteList(Long userId) {
        UserEntity user = userRepository.findById(userId).orElse(null);
        if(user != null){
            List<MovieEntity> L = new ArrayList<>();
            favoritesRepository.findByUser(user).stream().forEach(w -> L.add(w.getMovie()));
            return L.stream().map(dtoMapper::fromMovie).collect(Collectors.toList());
        } else throw new UserNotFoundException("User not found");
    }











    //============================================================\\
    public String encryptionMd5(String passwordToHash){
        String generatedPassword = null;
        try {
            // Create MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("MD5");

            // Add password bytes to digest
            md.update(passwordToHash.getBytes());

            // Get the hash's bytes
            byte[] bytes = md.digest();

            // This bytes[] has bytes in decimal format. Convert it to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }

            // Get complete hashed password in hex format
            generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return generatedPassword;
    }
}
