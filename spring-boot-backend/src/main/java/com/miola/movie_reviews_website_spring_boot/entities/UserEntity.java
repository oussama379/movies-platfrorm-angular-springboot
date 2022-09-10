
package com.miola.movie_reviews_website_spring_boot.entities;


import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Data
@ToString
public class UserEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String fullname;
    private String username;
    private String email;
    private String password;
    @OneToMany(mappedBy = "user")
    private Set<Watched> watchedList;
    @OneToMany(mappedBy = "user")
    private Set<Favorites> favorites;
    @OneToMany(mappedBy = "user")
    private Set<Wishes> wishes;
    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", fullname='" + fullname + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
