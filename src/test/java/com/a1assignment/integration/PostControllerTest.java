package com.a1assignment.integration;

import com.a1assignment.BaseIntegrationTest;
import com.a1assignment.dto.CreatePostRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class PostControllerTest extends BaseIntegrationTest {

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
            ResultActions resultActions = mockMvc.perform(post("/api/posts")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(createPostRequest))
                    .accept(MediaType.APPLICATION_JSON))
                    .andDo(print());

            //then
            resultActions
                    .andExpect(status().isCreated());

        }
    }
}