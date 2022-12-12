package com.sparta.week4_level2.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@NoArgsConstructor  //(롬복)
@Getter  //(롬복)

public class SignupRequestDto {
    @Size(min = 4, max = 10, message = "아이디의 길이는 최소 4자 이상, 10자 이하여야 합니다.")
    @Pattern(regexp = "[a-z0-9]*$", message = "아이디의 형식이 일치하지 않습니다.")
    private String username;

    @Size(min = 8, max = 15, message = "비밀번호의 길이는 최소 8자 이상, 15자 이하여야 합니다.")
    @Pattern(regexp = "[a-zA-Z0-9]", message = "비밀번호의 형식이 일치하지 않습니다.")
    private String password;
    private String email;
    private boolean admin = false;
    private String adminToken = "";
}
