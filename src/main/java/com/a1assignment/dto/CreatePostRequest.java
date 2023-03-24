package com.a1assignment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreatePostRequest {

    @NotNull(message = "닉네임 입력은 필수 입니다.")
    private String nickname;

    @Size(min = 1, max = 100, message = "1~100글자 이내로 입력해주세요.")
    private String title;

    @NotBlank(message = "공백 입력은 불가합니다.")
    private String content;
    private boolean isChecked;
}
