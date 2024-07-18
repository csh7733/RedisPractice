package com.example.redis.practice.dto;

import lombok.Data;

@Data
public class RequestLoginDto {
    private String username;
    private String email;
    private String password;
}
