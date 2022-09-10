package com.miola.movie_reviews_website_spring_boot.jsonModels;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;


import java.util.Collection;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class Movie {

    @JsonProperty("title")
    private String title;

    @JsonProperty("original_title")
    private String originalTitle;

    @JsonProperty("popularity")
    private float popularity;

    @JsonProperty("backdrop_path")
    private String backdropPath;

    @JsonProperty("poster_path")
    private String posterPath;

    @JsonProperty("release_date")
    private String releaseDate;

    @JsonProperty("adult")
    private boolean adult;

    @JsonProperty("budget")
    private long budget;

    @JsonProperty("genre_ids")
    private List<Long> genres;

    @JsonProperty("homepage")
    private String homepage;

    @JsonProperty("overview")
    private String overview;

    @JsonProperty("imdb_id")
    private String imdbID;

    @JsonProperty("original_language")
    private String originalLanguage;


    @JsonProperty("revenue")
    private long revenue;

    @JsonProperty("runtime")
    private int runtime;

    @JsonProperty("tagline")
    private String tagline;

    @JsonProperty("rating")
    private float userRating;

    @JsonProperty("vote_average")
    private float voteAverage;

    @JsonProperty("vote_count")
    private int voteCount;

    @JsonProperty("status")
    private String status;

    @JsonProperty("id")
    private Long id;

    @JsonProperty("trailers")
    private List<Trailer> trailerResult;

}
