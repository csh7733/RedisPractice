package com.example.redis.practice.service;

import com.example.redis.practice.domain.Posts;
import com.example.redis.practice.dto.ResponseLeaderboardDto;
import com.example.redis.practice.dto.ResponsePostsDto;
import com.example.redis.practice.repository.posts.PostsRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class PostsService {

    private final PostsRepository postsRepository;

    public void save(String notice) {
        Posts posts = new Posts(notice);
        postsRepository.save(posts);
    }
    @Cacheable(value = "posts", key = "'notice'", unless = "#result == null || #result.isEmpty()")
    public List<ResponsePostsDto> getNotice() {

        return postsRepository.findAll().stream()
                .map(ResponsePostsDto::new)
                .collect(Collectors.toList());

    }
}
