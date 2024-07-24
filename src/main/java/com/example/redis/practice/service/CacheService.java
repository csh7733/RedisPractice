package com.example.redis.practice.service;

import com.example.redis.practice.dto.ResponseCommentDto;
import com.example.redis.practice.dto.ResponsePostsDto;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CacheService {

    @Cacheable(value = "posts", key = "'notice'", unless = "#result == null || #result.isEmpty()")
    public List<ResponsePostsDto> getNoticesFromCache() {
        return new ArrayList<>();
    }

    @Cacheable(value = "cache", key = "'comment'", unless = "#result == null || #result.isEmpty()")
    public List<ResponseCommentDto> getCommentFromCache() {
        return new ArrayList<>();
    }
}
