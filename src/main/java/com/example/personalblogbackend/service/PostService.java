package com.example.personalblogbackend.service;

import com.example.personalblogbackend.model.Post;
import com.example.personalblogbackend.model.Theme;
import com.example.personalblogbackend.model.User;

import java.util.List;
import java.util.Optional;

public interface PostService {
    Post createPost(Post post);
    Optional<Post> getPostById(Long id);
    List<Post> getAllPosts();
    Post updatePost(Long id, Post post);
    void deletePost(Long id);
    List<Post> getPostsByAuthor(User author);
    List<Post> getPostsByTheme(Theme theme);
    List<Post> getPostsByAuthorAndTheme(User author, Theme theme);
}
