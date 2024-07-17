package com.example.redis.practice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LikeResponseDto {
    private String postId;
    private Long likeCount;
    private Set<String> members;
}
