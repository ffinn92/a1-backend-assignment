package com.a1assignment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DeletePostRequest {

    @NotNull(message = "요청 게시판 식별자는 필수 입니다.")
    private Long id;
}
