package com.sparta.week4_level2.entity;

import com.sparta.week4_level2.dto.CommentRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity //(javax.persistence)
@NoArgsConstructor //(롬복)
@Getter //(롬복)
public class Comment extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    public Comment(CommentRequestDto requestDto, Long id){
        this.id = requestDto.getId();
        this.username = requestDto.getUsername();
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();

    }

    public void create(CommentRequestDto commentRequestDto){
        this.id = commentRequestDto.getId();
        this.username = commentRequestDto.getUsername();
        this.content = commentRequestDto.getContent();

    }

    public void update(CommentRequestDto commentRequestDto){
        this.id = commentRequestDto.getId();
        this.username = commentRequestDto.getUsername();
        this.content = commentRequestDto.getContent();
    }

    public void delete(CommentRequestDto commentRequestDto){
        this.id = commentRequestDto.getId();
        this.username = commentRequestDto.getUsername();
        this.content = commentRequestDto.getContent();
    }

}
