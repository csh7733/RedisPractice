
package com.example.redis.practice.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.ZSetOperations;

@Data
@NoArgsConstructor
public class ResponseLikeRankDto {

    private String postId;
    private double score;

    public ResponseLikeRankDto(ZSetOperations.TypedTuple<Object> tuple) {
        this.postId = (String) tuple.getValue();
        this.score = tuple.getScore();
    }
}
