package com.example.personalblogbackend.repository;

import com.example.personalblogbackend.model.Post;
import com.example.personalblogbackend.model.Theme;
import com.example.personalblogbackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findByAuthor(User author);

    List<Post> findByTheme(Theme theme);

    @Query("SELECT p FROM Post p WHERE (:author IS NULL OR p.author = :author) AND (:theme IS NULL OR p.theme = :theme)")
    List<Post> findByAuthorAndTheme(@Param("author") User author, @Param("theme") Theme theme);
}
