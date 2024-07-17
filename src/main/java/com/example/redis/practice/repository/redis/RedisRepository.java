package com.example.redis.practice.repository.redis;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Repository
@RequiredArgsConstructor
public class RedisRepository {

    private final RedisTemplate<String, Object> redisTemplate;

    public void saveString(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
    }
    public String getString(String key) {
        return Optional.ofNullable(redisTemplate.opsForValue().get(key))
                .map(Object::toString)
                .orElse(null);
    }

    public void saveHash(String key, Map<String, Object> hash) {
        redisTemplate.opsForHash().putAll(key, hash);
    }

    public Map<Object, Object> getHash(String key) {
        return redisTemplate.opsForHash().entries(key);
    }

    public void saveList(String key, List<String> list) {
        redisTemplate.opsForList().rightPushAll(key, list);
    }

    public List<Object> getList(String key) {
        return redisTemplate.opsForList().range(key, 0, -1);
    }

    public void saveSet(String key, Set<String> set) {
        redisTemplate.opsForSet().add(key, set.toArray());
    }

    public Set<Object> getSet(String key) {
        return redisTemplate.opsForSet().members(key);
    }

    public void saveZSet(String key, String value, Double score) {
        redisTemplate.opsForZSet().add(key, value, score);
    }

    public Set<Object> getZSet(String key, Double min, Double max) {
        return redisTemplate.opsForZSet().rangeByScore(key, min, max);
    }

    public Set<ZSetOperations.TypedTuple<Object>> getTopScores(String key, int top) {
        return redisTemplate.opsForZSet().reverseRangeWithScores(key, 0, top - 1);
    }
}
