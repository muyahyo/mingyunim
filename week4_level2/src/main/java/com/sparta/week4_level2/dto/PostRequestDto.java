package com.sparta.week4_level2.dto;

import lombok.Getter;

@Getter
public class PostRequestDto {
    private String title;
    private String content;
    private Long id;
    private String username;
    private String password;

}
