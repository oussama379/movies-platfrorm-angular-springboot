package com.miola.movie_reviews_website_spring_boot.entities;


import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@ToString
public class ReviewEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String reviewText;
    // rating with starts
    private double rating;
    private String reviewHeadLine;
    private Date reviewDate;
    @ManyToOne
    @JoinColumn(name = "movie_id")
    private MovieEntity movie;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;


    @Override
    public String toString() {
        return "ReviewEntity{" +
                "id=" + id +
                ", reviewText='" + reviewText + '\'' +
                ", rating=" + rating +
                ", reviewHeadLine='" + reviewHeadLine + '\'' +
                ", reviewDate=" + reviewDate +
                '}';
    }
}
