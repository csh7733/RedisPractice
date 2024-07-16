package com.example.redis.practice.service;

import com.example.redis.practice.repository.RedisRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class RedisService {

    private final RedisRepository redisRepository;

    public void saveString(String key, String value) {
        redisRepository.saveString(key, value);
    }

    public String getString(String key) {
        return redisRepository.getString(key);
    }

    public void saveHash(String key, Map<String, Object> hash) {
        redisRepository.saveHash(key, hash);
    }

    public Map<Object, Object> getHash(String key) {
        return redisRepository.getHash(key);
    }

    public void saveList(String key, List<String> list) {
        redisRepository.saveList(key, list);
    }

    public List<Object> getList(String key) {
        return redisRepository.getList(key);
    }

    public void saveSet(String key, Set<String> set) {
        redisRepository.saveSet(key, set);
    }

    public Set<Object> getSet(String key) {
        return redisRepository.getSet(key);
    }

    public void saveZSet(String key, String value, double score) {
        redisRepository.saveZSet(key, value, score);
    }

    public Set<Object> getZSet(String key, double min, double max) {
        return redisRepository.getZSet(key, min, max);
    }
}
