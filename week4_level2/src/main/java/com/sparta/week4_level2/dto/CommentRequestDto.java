package com.sparta.week4_level2.dto;

import lombok.Getter;

@Getter
public class CommentRequestDto {
    private String title;
    private String content;
    private String username;
    private Long id;
}
