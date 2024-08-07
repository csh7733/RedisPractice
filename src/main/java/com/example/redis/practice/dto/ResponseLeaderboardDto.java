package com.example.redis.practice.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.ZSetOperations;

@Data
@NoArgsConstructor
public class ResponseLeaderboardDto {

    private String member;
    private double score;

    public ResponseLeaderboardDto(ZSetOperations.TypedTuple<Object> tuple) {
        this.member = (String) tuple.getValue();
        this.score = tuple.getScore();
    }
}
