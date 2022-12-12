package com.sparta.week4_level2.service;

import com.sparta.week4_level2.dto.PostRequestDto;
import com.sparta.week4_level2.dto.PostResponseDto;
import com.sparta.week4_level2.entity.Post;
import com.sparta.week4_level2.entity.User;
import com.sparta.week4_level2.jwt.JwtUtil;
import com.sparta.week4_level2.repository.PostRepository;
import com.sparta.week4_level2.repository.UserRepository;
import com.sun.nio.sctp.IllegalReceiveException;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    @Transactional
    public PostResponseDto createPost(PostRequestDto requestDto, HttpServletRequest request) {
        String token = jwtUtil.resolveToken(request);
        Claims claims;

        if (token != null) {
            if (jwtUtil.validateToken(token)) {
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new IllegalArgumentException("Token Error");
            }
            User user = userRepository.findByUsername(claims.getSubject()).orElseThrow(
                    () -> new IllegalArgumentException("사용자가 존재하지 않습니다."));

            Post post = postRepository.save(new Post(requestDto));
            return new PostResponseDto(post);
        } else {
            return null;
        }
    }

    @Transactional(readOnly = true)
    public List<PostResponseDto> getListPosts(){
        List<Post> postList = postRepository.findAllByOrderByModifiedAtDesc();
        List<PostResponseDto> postResponseDto = new ArrayList<>();
        for (Post post : postList){
            PostResponseDto postDto = new PostResponseDto(post);
            postResponseDto.add(postDto);

        }
        return postResponseDto;
    }

    @Transactional(readOnly = true)
    public PostResponseDto getPost(long id){
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
        return new PostResponseDto(post);
    }

    @Transactional
    public PostResponseDto update(Long id, PostRequestDto requestDto, HttpServletRequest request){
        String token = jwtUtil.resolveToken(request);
        Claims claims;

        if(token != null){
            if(jwtUtil.validateToken(token)) {
                claims = jwtUtil.getUserInfoFromToken(token);
                }else{
                throw new IllegalReceiveException("Token Error");
            }

            User user = userRepository.findByUsername(claims.getSubject()).orElseThrow(
                    () -> new IllegalArgumentException("사용자가 존재하지 않습니다.")
            );

            Post post = postRepository.findById(id).orElseThrow(
                    () -> new IllegalReceiveException("아이디가 존재하지 않습니다.")
            );
            post.update(requestDto);

            return new PostResponseDto(post);
        } else {
            return null;
        }
    }

    public Long deletePost(Long id, PostRequestDto requestDto, HttpServletRequest request){
        String token = jwtUtil.resolveToken(request);
        Claims claims;

        if(token != null){
            if(jwtUtil.validateToken(token)){
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {throw new IllegalArgumentException("Token Error");}
            User user = userRepository.findByUsername(claims.getSubject()).orElseThrow(
                    () -> new IllegalArgumentException("사용자가 존재하지 않습니다.")
            );

            Post post = postRepository.findById(id).orElseThrow(
                    () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
            );

            post.delete(requestDto);

            return post.getId();
        } else {
            return null;
        }

    }


}
