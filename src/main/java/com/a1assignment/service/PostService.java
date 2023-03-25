package com.a1assignment.service;

import com.a1assignment.dto.DeletePostRequest;
import com.a1assignment.dto.CreatePostRequest;
import com.a1assignment.dto.ResponseList;
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
        Post post = postRepository.findById(updatePostRequest.getId())
                .orElseThrow(() -> new NoSuchElementException("해당하는 Post가 없습니다."));

        post.updatePost(updatePostRequest.getNickname(),
                             updatePostRequest.getTitle(),
                             updatePostRequest.getContent(),
                             updatePostRequest.isChecked());

        return post.getId();
    }

    @Transactional(readOnly = true)
    public ResponseList searchPosts() {
        List<Post> posts = postRepository.findAll();

        List<SearchPostResponse> searchPostResponses = posts
                .stream()
                .map(SearchPostResponse::from)
                .collect(Collectors.toList());

        return new ResponseList(searchPostResponses);
    }

    @Transactional(readOnly = true)
    public ResponseList searchPostsByNickname(String nickname) {
        List<Post> posts = postRepository.findByNickname(nickname);

        List<SearchPostResponse> searchPostResponses = posts
                .stream()
                .map(SearchPostResponse::from)
                .collect(Collectors.toList());

        return new ResponseList(searchPostResponses);
    }

    @Transactional(readOnly = true)
    public ResponseList searchPostsByTitle(String title) {
        List<Post> posts = postRepository.findByTitle(title);

        List<SearchPostResponse> searchPostResponses = posts
                .stream()
                .map(SearchPostResponse::from)
                .collect(Collectors.toList());

        return new ResponseList(searchPostResponses);
    }

    @Transactional
    public void deletePost(DeletePostRequest deletePostRequest) {
        Post post = postRepository.findById(deletePostRequest.getId())
                .orElseThrow(() -> new NoSuchElementException("해당하는 Post가 없습니다."));

        post.delete();
        postRepository.save(post);
    }

}
