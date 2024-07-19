package com.example.redis.practice.service;

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
}
