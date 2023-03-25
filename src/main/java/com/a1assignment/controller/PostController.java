package com.a1assignment.controller;

import com.a1assignment.dto.CreatePostRequest;
import com.a1assignment.dto.DeletePostRequest;
import com.a1assignment.dto.ResponseList;
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
    public ResponseEntity<ResponseList> searchPosts() {
        return new ResponseEntity<>(postService.searchPosts(), HttpStatus.OK);
    }

    @GetMapping("/nickname")
    public ResponseEntity<ResponseList> searchPostsByNickname(@RequestParam String nickname) {
        return new ResponseEntity<>(postService.searchPostsByNickname(nickname), HttpStatus.OK);
    }

    @GetMapping("/title")
    public ResponseEntity<ResponseList> searchPostsBytitle(@RequestParam String title) {
        return new ResponseEntity<>(postService.searchPostsByTitle(title), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<Void> deletePost(@Valid @RequestBody DeletePostRequest deletePostRequest) {
        postService.deletePost(deletePostRequest);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
