package com.a1assignment.entity;

import com.a1assignment.common.BaseTime;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nickname;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(nullable = false)
    private boolean priority;

    @Column(nullable = false)
    private boolean isDeleted;

    public Post(String nickname, String title, String content, boolean priority) {
        this.nickname = nickname;
        this.title = title;
        this.content = content;
        this.priority = priority;
    }

    public void updatePost(String nickname, String title, String content, boolean priority) {
        this.nickname = nickname;
        this.title = title;
        this.content = content;
        this.priority = priority;
    }

    public void updatePostPriority(String nickname, String title, String content, boolean priority) {
        this.nickname = nickname;
        this.title = title;
        this.content = content;
        this.priority = priority;
    }

    public void delete() {
        this.isDeleted = true;
    }
}
