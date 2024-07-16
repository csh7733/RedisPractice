package com.example.redis.practice.dto;

import lombok.Data;
import org.springframework.data.redis.core.ZSetOperations;

@Data
public class ResponseLeaderboardDto {

    private String member;
    private double score;

    public ResponseLeaderboardDto(ZSetOperations.TypedTuple<Object> tuple) {
        this.member = (String) tuple.getValue();
        this.score = tuple.getScore();
    }
}
