package com.a1assignment.integration;

import com.a1assignment.BaseIntegrationTest;
import com.a1assignment.dto.CreatePostRequest;
import com.a1assignment.dto.UpdatePostRequest;
import com.a1assignment.entity.Post;
import com.a1assignment.repository.PostRepository;
import com.a1assignment.service.PostService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PostControllerTest extends BaseIntegrationTest {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private PostService postService;

    @Nested
    @DisplayName("게시글 작성 시")
    class PostCreate {

        @Test
        void 닉네임과_제목_본문_정보를_전달받으면_게시글로_저장한다() throws Exception {
            //given
            String nickname = "게시글 작성 테스트_닉네임";
            String title = "게시글 작성 테스트_제목";
            String content = "게시글 작성 테스트_본문";
            boolean isChecked = false;
            CreatePostRequest createPostRequest = new CreatePostRequest(nickname,
                                                                        title,
                                                                        content,
                                                                        isChecked);

            //when
            long savedPostId = postService.createPost(createPostRequest);

            //then
            Post savedPost = postRepository.findById(savedPostId).orElseThrow();
            List<Post> posts = postRepository.findAll();
            assertThat(posts.size()).isEqualTo(1);
            assertThat(createPostRequest.getNickname()).isEqualTo(savedPost.getNickname());
            assertThat(createPostRequest.getTitle()).isEqualTo(savedPost.getTitle());
            assertThat(createPostRequest.getContent()).isEqualTo(savedPost.getContent());
            assertThat(createPostRequest.isChecked()).isEqualTo(savedPost.isChecked());
        }
    }

    @Nested
    @DisplayName("게시글 수정 시")
    class UpdatePost {

        @Test
        void 닉네임과_수정할_제목을_전달받으면_기존_게시글의_제목정보를_수정하여_저장한다() throws Exception {
            //given
            Long id = 1L;
            String nickname = "게시글 수정 테스트_기존 닉네임";
            String title = "게시글 수정 테스트_기존 제목";
            String content = "게시글 수정 테스트_기존 본문";
            boolean isChecked = false;

            Post post = new Post(nickname, title, content, isChecked);
            ReflectionTestUtils.setField(post, "id", id);

            Post savedPost = postRepository.save(post);

            String updateTile = "게시글 수정 테스트_수정 제목";
            UpdatePostRequest updatePostRequest = new UpdatePostRequest(savedPost.getId(),
                                                                        savedPost.getNickname(),
                                                                        updateTile,
                                                                        savedPost.getContent(),
                                                                        savedPost.isChecked());

            //when
            long updatedPostId = postService.updatePost(updatePostRequest);

            //then
            Post updatedPost = postRepository.findById(updatedPostId).orElseThrow();
            List<Post> posts = postRepository.findAll();
            assertThat(posts.size()).isEqualTo(1);
            assertThat(updatePostRequest.getId()).isEqualTo(updatedPost.getId());
            assertThat(updatePostRequest.getNickname()).isEqualTo(updatedPost.getNickname());
            assertThat(updatePostRequest.getTitle()).isEqualTo(updatedPost.getTitle());
            assertThat(updatePostRequest.getContent()).isEqualTo(updatedPost.getContent());
            assertThat(updatePostRequest.isChecked()).isEqualTo(updatedPost.isChecked());
        }

        @Test
        void 닉네임과_수정할_본문을_전달받으면_기존_게시글의_본문정보를_수정하여_저장한다() throws Exception {
            //given
            Long id = 1L;
            String nickname = "게시글 수정 테스트_기존 닉네임";
            String title = "게시글 수정 테스트_기존 제목";
            String content = "게시글 수정 테스트_기존 본문";
            boolean isChecked = false;

            Post post = new Post(nickname, title, content, isChecked);
            ReflectionTestUtils.setField(post, "id", id);

            Post savedPost = postRepository.save(post);

            String updateContent = "게시글 수정 테스트_수정 본문";
            UpdatePostRequest updatePostRequest = new UpdatePostRequest(savedPost.getId(),
                                                                        savedPost.getNickname(),
                                                                        savedPost.getTitle(),
                                                                        updateContent,
                                                                        savedPost.isChecked());

            //when
            long updatedPostId = postService.updatePost(updatePostRequest);

            //then
            Post updatedPost = postRepository.findById(updatedPostId).orElseThrow();
            List<Post> posts = postRepository.findAll();
            assertThat(posts.size()).isEqualTo(1);
            assertThat(updatePostRequest.getId()).isEqualTo(updatedPost.getId());
            assertThat(updatePostRequest.getNickname()).isEqualTo(updatedPost.getNickname());
            assertThat(updatePostRequest.getTitle()).isEqualTo(updatedPost.getTitle());
            assertThat(updatePostRequest.getContent()).isEqualTo(updatedPost.getContent());
            assertThat(updatePostRequest.isChecked()).isEqualTo(updatedPost.isChecked());
        }

        @Test
        void 유효하지_않은_게시글_id로_수정요청시_예외를_발생시킨다() throws Exception {
            //given
            Long id = 1L;
            String nickname = "게시글 수정 테스트_기존 닉네임";
            String title = "게시글 수정 테스트_기존 제목";
            String content = "게시글 수정 테스트_기존 본문";
            boolean isChecked = false;

            Post post = new Post(nickname, title, content, isChecked);
            ReflectionTestUtils.setField(post, "id", id);

            Post savedPost = postRepository.save(post);

            String updateContent = "게시글 수정 테스트_수정 본문";
            Long invalidId = 2L;
            UpdatePostRequest updatePostRequest = new UpdatePostRequest(invalidId,
                                                                        savedPost.getNickname(),
                                                                        savedPost.getTitle(),
                                                                        updateContent,
                                                                        savedPost.isChecked());

            //then
            assertThatThrownBy(() -> postService.updatePost(updatePostRequest)).isInstanceOf(NoSuchElementException.class);
        }
    }
}