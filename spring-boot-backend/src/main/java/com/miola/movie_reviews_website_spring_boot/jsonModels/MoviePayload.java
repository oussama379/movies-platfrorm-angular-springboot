package com.miola.movie_reviews_website_spring_boot.jsonModels;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class MoviePayload {

    @JsonProperty("movie_fields")
    Movie movie;

    @JsonProperty("movie_trailers")
    List<Trailer> trailerResult;

    @JsonProperty("similar_movies")
    List<Movie> similarMovies;

    @JsonProperty("cast_and_crew")
    List<Crew> crew;

}
