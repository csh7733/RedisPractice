package com.example.redis.practice.repository.redis;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.Set;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class LikeRepository {

    private final RedisTemplate<String, Object> redisTemplate;

    public void likePost(String key, String member) {
        redisTemplate.opsForSet().add(key, member);
    }

    public void unlikePost(String key, String member) {
        redisTemplate.opsForSet().remove(key, member);
    }

    public Set<String> getLikes(String key) {
        return redisTemplate.opsForSet().members(key).stream()
                .map(Object::toString)
                .collect(Collectors.toSet());
    }

    public boolean isLiked(String key, String member) {
        return redisTemplate.opsForSet().isMember(key, member);
    }

    public Long getLikeCount(String key) {
        return redisTemplate.opsForSet().size(key);
    }
    public void updateLikeScore(String postId, double score) {
        redisTemplate.opsForZSet().add("likesRank", postId, score);
    }
}
