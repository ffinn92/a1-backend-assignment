package com.a1assignment.dto;

import com.a1assignment.entity.Post;
import lombok.Getter;

@Getter
public class SearchPostResponse {

    private final Long id;
    private final String nickname;
    private final String title;
    private final boolean isChecked;

    public SearchPostResponse(Long id, String nickname, String title, boolean isChecked) {
        this.id = id;
        this.nickname = nickname;
        this.title = title;
        this.isChecked = isChecked;
    }

    public static SearchPostResponse from(Post post) {
        return new SearchPostResponse(
                post.getId(),
                post.getNickname(),
                post.getTitle(),
                post.isChecked()
        );
    }
}
