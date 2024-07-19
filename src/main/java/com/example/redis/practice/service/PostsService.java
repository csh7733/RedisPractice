package com.example.redis.practice.service;

import com.example.redis.practice.domain.Posts;
import com.example.redis.practice.dto.ResponseLeaderboardDto;
import com.example.redis.practice.dto.ResponsePostsDto;
import com.example.redis.practice.repository.posts.PostsRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class PostsService {

    private final PostsRepository postsRepository;
    private final CacheService cacheService;

    public void save(String notice) {
        Posts posts = new Posts(notice);
        postsRepository.save(posts);
    }


    @CachePut(value = "posts", key = "'notice'")
    public List<ResponsePostsDto> saveNotice(Long id, String title) {
        List<ResponsePostsDto> currentNotices = cacheService.getNoticesFromCache();
        currentNotices.add(new ResponsePostsDto(id, title));
        save(title);
        return currentNotices;
    }

//    @Cacheable(value = "posts", key = "'notice'", unless = "#result == null || #result.isEmpty()")
//    public List<ResponsePostsDto> getNoticesFromCache() {
//        log.info("empty!");
//        return new ArrayList<>(); // 빈 리스트를 반환하여 캐시에서만 저장되도록 함
//    }
//
//    @CacheEvict(value = "posts", key = "'notice'", beforeInvocation = true)
//    public void evictNotice() {
//        List<String> notices = getNoticesFromCache();
//        if (notices != null) {
//            notices.forEach(notice -> postsRepository.save(new Posts(notice)));
//        }
//    }
    @Cacheable(value = "posts", key = "'notice'", unless = "#result == null || #result.isEmpty()")
    public List<ResponsePostsDto> getNotice() {

        return postsRepository.findAll().stream()
                .map(ResponsePostsDto::new)
                .collect(Collectors.toList());

    }

//    public List<ResponsePostsDto> getNotice() {
//        return new ArrayList<>();
//    }
}
