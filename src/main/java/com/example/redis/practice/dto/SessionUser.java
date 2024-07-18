package com.example.redis.practice.dto;

import lombok.Getter;

import java.io.Serializable;

@Getter
public class SessionUser implements Serializable {
    private String username;
    private String email;

    public SessionUser(RequestLoginDto requestLoginDto) {
        this.username = requestLoginDto.getUsername();
        this.email = requestLoginDto.getEmail();
    }

    @Override
    public String toString() {
        return "SessionUser{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}