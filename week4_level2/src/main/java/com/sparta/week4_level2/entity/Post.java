package com.sparta.week4_level2.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sparta.week4_level2.dto.PostRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Post extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private String content;
    @JsonIgnore //(jackson.annotation)
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String title;

    public Post(PostRequestDto requestDto){
        this.id = requestDto.getId();
        this.username = requestDto.getUsername();
        this.content = requestDto.getContent();
        this.password = requestDto.getPassword();
        this.title = requestDto.getTitle();
    }

    public void update(PostRequestDto postRequestDto){
        this.username = postRequestDto.getUsername();
        this.content = postRequestDto.getContent();
        this.title = postRequestDto.getTitle();
        this.password = postRequestDto.getPassword();
    }

    public  void delete(PostRequestDto postRequestDto){
        this.username = postRequestDto.getUsername();
        this.content = postRequestDto.getContent();
        this.title = postRequestDto.getTitle();
        this.password = postRequestDto.getPassword();
    }

}

