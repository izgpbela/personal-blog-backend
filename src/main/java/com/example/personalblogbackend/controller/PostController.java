package com.example.personalblogbackend.controller;

import com.example.personalblogbackend.model.Post;
import com.example.personalblogbackend.model.Theme;
import com.example.personalblogbackend.model.User;
import com.example.personalblogbackend.service.PostService;
import com.example.personalblogbackend.service.ThemeService;
import com.example.personalblogbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;
    private final UserService userService;
    private final ThemeService themeService;

    @Autowired
    public PostController(PostService postService, UserService userService, ThemeService themeService) {
        this.postService = postService;
        this.userService = userService;
        this.themeService = themeService;
    }

    @PostMapping
    public ResponseEntity<Post> createPost(@RequestBody Post post) {
        Post createdPost = postService.createPost(post);
        return ResponseEntity.ok(createdPost);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable Long id) {
        return postService.getPostById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Post>> getAllPosts(
            @RequestParam(required = false) Long authorId,
            @RequestParam(required = false) Long themeId) {

        Optional<User> author = authorId != null ? userService.getUserById(authorId) : Optional.empty();
        Optional<Theme> theme = themeId != null ? themeService.getThemeById(themeId) : Optional.empty();

        List<Post> posts;

        if (author.isPresent() && theme.isPresent()) {
            posts = postService.getPostsByAuthorAndTheme(author.get(), theme.get());
        } else if (author.isPresent()) {
            posts = postService.getPostsByAuthor(author.get());
        } else if (theme.isPresent()) {
            posts = postService.getPostsByTheme(theme.get());
        } else {
            posts = postService.getAllPosts();
        }

        return ResponseEntity.ok(posts);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Post> updatePost(@PathVariable Long id, @RequestBody Post post) {
        try {
            Post updatedPost = postService.updatePost(id, post);
            return ResponseEntity.ok(updatedPost);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
        postService.deletePost(id);
        return ResponseEntity.noContent().build();
    }
}
