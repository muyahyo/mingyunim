package com.sparta.week4_level2.service;


import com.sparta.week4_level2.dto.CommentRequestDto;
import com.sparta.week4_level2.dto.CommentResponseDto;
import com.sparta.week4_level2.entity.Comment;
import com.sparta.week4_level2.entity.User;
import com.sparta.week4_level2.jwt.JwtUtil;
import com.sparta.week4_level2.repository.CommentRepository;
import com.sparta.week4_level2.repository.UserRepository;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    public CommentResponseDto createComment( CommentRequestDto requestDto, HttpServletRequest request){
        String token = jwtUtil.resolveToken(request);
        Claims claims;
        if (token != null) {
            if (jwtUtil.validateToken(token)) {
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new IllegalArgumentException("Token Error");
            }
            User user = userRepository.findByUsername(claims.getSubject()).orElseThrow(
                    () -> new IllegalArgumentException("사용자가 존재하지 않습니다.")
            );
            Comment comment = commentRepository.saveAndFlush(new Comment(requestDto, user.getId()));

            comment.create(requestDto);
            return new CommentResponseDto(comment);
        } else {
            return null;
        }

    }


    @Transactional
    public Long updateComment(Long id, CommentRequestDto requestDto, HttpServletRequest request){
        String token = jwtUtil.resolveToken(request);
        Claims claims;
        if (token != null) {
            if (jwtUtil.validateToken(token)) {
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new IllegalArgumentException("Token Error");
            }
            User user = userRepository.findByUsername(claims.getSubject()).orElseThrow(
                    () -> new IllegalArgumentException("사용자가 존재하지 않습니다.")
            );
            Comment comment = commentRepository.findById(id).orElseThrow(
                    () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
            );

            comment.update(requestDto);
            return comment.getId();
        } else {
            return null;
        }
    }

    @Transactional
    public Long deleteComment(Long id, CommentRequestDto requestDto, HttpServletRequest request){
        String token = jwtUtil.resolveToken(request);
        Claims claims;
        if (token != null) {
            if (jwtUtil.validateToken(token)) {
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new IllegalArgumentException("Token Error");
            }
            User user = userRepository.findByUsername(claims.getSubject()).orElseThrow(
                    () -> new IllegalArgumentException("사용자가 존재하지 않습니다.")
            );
            Comment comment = commentRepository.findById(id).orElseThrow(
                    () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
            );

            comment.delete(requestDto);
            return comment.getId();
        } else {
            return null;
        }
    }
}
