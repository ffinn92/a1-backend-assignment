package com.a1assignment.service;

import com.a1assignment.dto.CreatePostRequest;
import com.a1assignment.dto.UpdatePostRequest;
import com.a1assignment.entity.Post;
import com.a1assignment.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

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
}
