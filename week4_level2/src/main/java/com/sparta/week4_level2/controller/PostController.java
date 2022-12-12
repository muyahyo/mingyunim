package com.sparta.week4_level2.controller;

import com.sparta.week4_level2.dto.PostRequestDto;
import com.sparta.week4_level2.dto.PostResponseDto;
import com.sparta.week4_level2.dto.ResponseMsgDto;
import com.sparta.week4_level2.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController //(스프링)
@RequiredArgsConstructor //(롬복)
@RequestMapping("/api") //(스프링)

public class PostController {
    private final PostService postService;

    @PutMapping("/post")
    public PostResponseDto createPost(@RequestBody PostRequestDto requestDto, HttpServletRequest request) {
        return postService.createPost(requestDto, request);
    }

    @GetMapping("/posts") //(스프링) 게시글 목록 조회
    public List<PostResponseDto> getListPosts() {
        return postService.getListPosts();
    }

    @GetMapping("/post/{id}") //(스프링) 게시글 상세 조회
    public PostResponseDto getPosts(@PathVariable long id) {
        return postService.getPost(id);
    }

    @PutMapping("/post/{id}")  //(스프링) 게시글 수정
    public PostResponseDto updatePost(@PathVariable Long id, @RequestBody PostRequestDto requestDto, HttpServletRequest request){
        return  postService.update(id, requestDto, request);
    }

    @DeleteMapping("/post/{id}") //(스프링) 게시글 삭제
    public ResponseMsgDto deletePost(@PathVariable long id, @RequestBody PostRequestDto requestDto, HttpServletRequest request){
        postService.deletePost(id, requestDto, request);
        return new ResponseMsgDto("게시글 삭제 성공", HttpStatus.OK.value());
    }



}
