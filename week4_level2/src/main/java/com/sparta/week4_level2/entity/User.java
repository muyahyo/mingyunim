package com.sparta.week4_level2.entity;


import lombok.Generated;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING) //Enum 타입 매핑시 사용하는 어노테이션
    private UserRoleEnum role;

    public User(String username, String password, String email, UserRoleEnum role){
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;

    }
}
