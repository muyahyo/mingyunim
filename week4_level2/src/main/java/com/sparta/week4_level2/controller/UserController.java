package com.sparta.week4_level2.controller;

import com.sparta.week4_level2.dto.*;
import com.sparta.week4_level2.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")

public class UserController {

    private final UserService userService;

    @PostMapping("/signup") //회원가입 API
    public ResponseMsgDto signup(@RequestBody SignupRequestDto signupRequestDto){
        userService.signup(signupRequestDto);
        return new ResponseMsgDto("회원가입 성공", HttpStatus.OK.value());
    }


    @ResponseBody
    @PostMapping("/login")
    public ResponseMsgDto login(@RequestBody LoginRequestDto loginRequestDto, HttpServletResponse response){
        userService.login(loginRequestDto, response);
        return new ResponseMsgDto("로그인 성공", HttpStatus.OK.value());
    }
}
