package com.sparta.week4_level2.controller;


import com.sparta.week4_level2.dto.CommentRequestDto;
import com.sparta.week4_level2.dto.CommentResponseDto;
import com.sparta.week4_level2.dto.ResponseMsgDto;
import com.sparta.week4_level2.service.CommentService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comment")
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/{id}")
    public CommentResponseDto createComment(@RequestBody CommentRequestDto requestDto, @RequestHeader HttpServletRequest request){
        return commentService.createComment(requestDto, request);
    }

    @PutMapping("/{id}")
    public Long updateComment(@PathVariable long id, @RequestBody CommentRequestDto requestDto, @RequestHeader HttpServletRequest request) {
        return commentService.updateComment(id, requestDto, request);
    }

    @DeleteMapping("/{id}")
    public ResponseMsgDto deleteComment(@PathVariable long id, @RequestBody CommentRequestDto requestDto, @RequestHeader HttpServletRequest request) {
        commentService.deleteComment(id, requestDto, request);
        return new ResponseMsgDto("로그인 성공", HttpStatus.OK.value());
    }
}
