package com.miola.movie_reviews_website_spring_boot;

import com.miola.movie_reviews_website_spring_boot.entities.UserEntity;
import com.miola.movie_reviews_website_spring_boot.repos.MovieRepository;
import com.miola.movie_reviews_website_spring_boot.repos.ReviewRepository;
import com.miola.movie_reviews_website_spring_boot.repos.UserRepository;
import org.springframework.aop.TargetSource;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.util.stream.Stream;

@SpringBootApplication
public class MovieReviewsWebSiteSpringBootApplication {

    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

    public static void main(String[] args) {
        SpringApplication.run(MovieReviewsWebSiteSpringBootApplication.class, args);
    }

    //@Bean
    CommandLineRunner start(UserRepository userRepository,
                            ReviewRepository reviewRepository,
                            MovieRepository movieRepository){
        return args -> {
            Stream.of("Hassan","Yassine","Aicha").forEach(name->{
                UserEntity user=new UserEntity();
                user.setFullname(name);
                user.setUsername(name);
                user.setPassword("1234");
                user.setEmail(name+"@gmail.com");
                userRepository.save(user);
            });
        };

    }

}
