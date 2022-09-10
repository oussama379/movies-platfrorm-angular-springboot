package com.miola.movie_reviews_website_spring_boot.entities;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Data
@ToString
public class Watched {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER)
    private UserEntity user;
    @ManyToOne(fetch = FetchType.EAGER)
    private MovieEntity movie;

}


