package com.a1assignment.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class ResponseList<T> {

    private final Integer count;
    private final List<T> content;

    public ResponseList(List<T> content) {
        this.count = content.size();
        this.content = content;
    }
}
