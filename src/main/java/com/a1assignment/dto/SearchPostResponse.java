package com.a1assignment.dto;

import com.a1assignment.entity.Post;
import lombok.Getter;

@Getter
public class SearchPostResponse {

    private final Long id;
    private final String nickname;
    private final String title;
    private final String content;
    private final boolean priority;

    public SearchPostResponse(Long id, String nickname, String title, String content, boolean priority) {
        this.id = id;
        this.nickname = nickname;
        this.title = title;
        this.content = content;
        this.priority = priority;
    }

    public static SearchPostResponse searchPost(Post post) {
        return new SearchPostResponse(
                post.getId(),
                post.getNickname(),
                post.getTitle(),
                post.getContent(),
                post.isPriority()
        );
    }

    public static SearchPostResponse searchPosts(Post post) {
        return new SearchPostResponse(
                post.getId(),
                post.getNickname(),
                post.getTitle(),
                " ",
                post.isPriority()
        );
    }
}
