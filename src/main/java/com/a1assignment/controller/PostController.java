package com.a1assignment.controller;

import com.a1assignment.dto.PostCreateRequest;
import com.a1assignment.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class PostController {

    private final PostService postService;

    @PostMapping("/posts")
    public ResponseEntity<Long> createPost(@Valid @RequestBody PostCreateRequest postCreateRequest) {
        long createdPostId = postService.createPost(postCreateRequest);
        return new ResponseEntity<>(createdPostId, HttpStatus.CREATED);
    }
}