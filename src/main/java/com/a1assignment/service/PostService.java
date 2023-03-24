package com.a1assignment.service;

import com.a1assignment.dto.PostCreateRequest;
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
    public long createPost(PostCreateRequest postCreateRequest) {
        Post savedPost = postRepository.save(new Post(postCreateRequest.getNickname(),
                                                      postCreateRequest.getTitle(),
                                                      postCreateRequest.getContent(),
                                                      postCreateRequest.isChecked()));
        return savedPost.getId();
    }
}
