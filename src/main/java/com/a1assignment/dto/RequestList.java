package com.a1assignment.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class RequestList<T> {

    private final Integer count;
    private final List<T> content;

    public RequestList(List<T> content) {
        this.count = content.size();
        this.content = content;
    }
}
