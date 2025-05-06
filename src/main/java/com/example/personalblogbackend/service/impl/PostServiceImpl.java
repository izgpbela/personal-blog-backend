package com.example.personalblogbackend.service.impl;

import com.example.personalblogbackend.model.Post;
import com.example.personalblogbackend.model.Theme;
import com.example.personalblogbackend.model.User;
import com.example.personalblogbackend.repository.PostRepository;
import com.example.personalblogbackend.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    @Autowired
    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public Post createPost(Post post) {
        post.setCreatedAt(LocalDateTime.now());
        post.setUpdatedAt(LocalDateTime.now());
        return postRepository.save(post);
    }

    @Override
    public Optional<Post> getPostById(Long id) {
        return postRepository.findById(id);
    }

    @Override
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    @Override
    public Post updatePost(Long id, Post post) {
        return postRepository.findById(id).map(existingPost -> {
            existingPost.setTitle(post.getTitle());
            existingPost.setContent(post.getContent());
            existingPost.setAuthor(post.getAuthor());
            existingPost.setTheme(post.getTheme());
            existingPost.setUpdatedAt(LocalDateTime.now());
            return postRepository.save(existingPost);
        }).orElseThrow(() -> new RuntimeException("Post not found with id " + id));
    }

    @Override
    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }

    @Override
    public List<Post> getPostsByAuthor(User author) {
        return postRepository.findByAuthor(author);
    }

    @Override
    public List<Post> getPostsByTheme(Theme theme) {
        return postRepository.findByTheme(theme);
    }

    @Override
    public List<Post> getPostsByAuthorAndTheme(User author, Theme theme) {
        return postRepository.findByAuthorAndTheme(author, theme);
    }
}
