package com.miola.movie_reviews_website_spring_boot.repos;

import com.miola.movie_reviews_website_spring_boot.entities.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByEmail(String username);
    //List<UserEntity> findAll();
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    @Query("select u from UserEntity u where u.username like %:username%")
    List<UserEntity> searchByUsername(@Param("username") String username);
    Page<UserEntity> findByUsername(String username, Pageable p);


}