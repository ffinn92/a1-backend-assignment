package com.a1assignment.controller;

import com.a1assignment.dto.*;
import com.a1assignment.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;

    @PostMapping
    public ResponseEntity<Long> createPost(@Valid @RequestBody CreatePostRequest createPostRequest) {
        return new ResponseEntity<>(postService.createPost(createPostRequest), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Long> updatePost(@Valid @RequestBody UpdatePostRequest updatePostRequest) {
        return new ResponseEntity<>(postService.updatePost(updatePostRequest), HttpStatus.OK);
    }

    @PutMapping("/priority")
    public ResponseEntity<Long> updatePriority(@Valid @RequestBody UpdatePostPriorityRequest updatePostPriorityRequest) {
        return new ResponseEntity<>(postService.updatePostPriority(updatePostPriorityRequest), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SearchPostResponse> searchPost(@PathVariable("id") Long id) {
        return new ResponseEntity<>(postService.searchPost(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<ResponseList> searchPosts() {
        return new ResponseEntity<>(postService.searchPosts(), HttpStatus.OK);
    }

    @GetMapping("/keywords")
    public ResponseEntity<ResponseList> searchPostsByKeywords(@RequestParam String keyword) {
        return new ResponseEntity<>(postService.searchPostsByKeywords(keyword), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<Void> deletePost(@Valid @RequestBody DeletePostRequest deletePostRequest) {
        postService.deletePost(deletePostRequest);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
