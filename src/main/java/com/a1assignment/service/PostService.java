package com.a1assignment.service;

import com.a1assignment.dto.CreatePostRequest;
import com.a1assignment.dto.RequestList;
import com.a1assignment.dto.SearchPostResponse;
import com.a1assignment.dto.UpdatePostRequest;
import com.a1assignment.entity.Post;
import com.a1assignment.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    @Transactional
    public long createPost(CreatePostRequest createPostRequest) {
        Post savedPost = postRepository.save(new Post(createPostRequest.getNickname(),
                                                      createPostRequest.getTitle(),
                                                      createPostRequest.getContent(),
                                                      createPostRequest.isChecked()));
        return savedPost.getId();
    }

    @Transactional
    public long updatePost(UpdatePostRequest updatePostRequest) {
        Post foundPost = postRepository.findById(updatePostRequest.getId())
                .orElseThrow(() -> new NoSuchElementException("해당하는 Post가 없습니다."));

        foundPost.updatePost(updatePostRequest.getNickname(),
                             updatePostRequest.getTitle(),
                             updatePostRequest.getContent(),
                             updatePostRequest.isChecked());

        return foundPost.getId();
    }

    @Transactional(readOnly = true)
    public RequestList searchPosts() {
        List<Post> posts = postRepository.findAll();

        List<SearchPostResponse> searchPostResponses = posts
                .stream()
                .map(SearchPostResponse::from)
                .collect(Collectors.toList());

        return new RequestList(searchPostResponses);
    }
}
