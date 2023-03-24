package com.a1assignment.service;

import com.a1assignment.dto.CreatePostRequest;
import com.a1assignment.entity.Post;
import com.a1assignment.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
