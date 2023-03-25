package com.a1assignment.controller;

import com.a1assignment.dto.CreatePostRequest;
import com.a1assignment.dto.RequestList;
import com.a1assignment.dto.UpdatePostRequest;
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
        long createdPostId = postService.createPost(createPostRequest);
        return new ResponseEntity<>(createdPostId, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Long> updatePost(@Valid @RequestBody UpdatePostRequest updatePostRequest) {
        long updatedPostId = postService.updatePost(updatePostRequest);
        return new ResponseEntity<>(updatedPostId, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<RequestList> searchPosts() {
        return new ResponseEntity<>(postService.searchPosts(), HttpStatus.OK);
    }
}
