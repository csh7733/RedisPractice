package com.example.redis.practice.service;

import com.example.redis.practice.domain.Comment;
import com.example.redis.practice.domain.Posts;
import com.example.redis.practice.dto.ResponseCommentDto;
import com.example.redis.practice.dto.ResponsePostsDto;
import com.example.redis.practice.repository.posts.CommentRepository;
import com.example.redis.practice.repository.posts.PostsRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class CommentService {

    private final CommentRepository commentRepository;
    private final CacheService cacheService;
    private final QueueService queueService;

    public void save(String author,String content) {
        Comment comment = new Comment(author,content);
        commentRepository.save(comment);
    }


    @CachePut(value = "cache", key = "'comment'")
    public List<ResponseCommentDto> saveComment(String author, String content) {
        List<ResponseCommentDto> currentComments = cacheService.getCommentFromCache();
        ResponseCommentDto addComment = new ResponseCommentDto(author, content);
        currentComments.add(addComment);
        log.info("save to Cache : comment = {}",addComment.toString());
        queueService.addCommentToQueue(addComment);
        return currentComments;
    }

    @Cacheable(value = "cache", key = "'comment'", unless = "#result == null || #result.isEmpty()")
    public List<ResponseCommentDto> getCommentsFromCache() {

        return commentRepository.findAll().stream()
                .map(ResponseCommentDto::new)
                .collect(Collectors.toList());

    }

    public List<ResponseCommentDto> getCommentsFromDB() {

        return commentRepository.findAll().stream()
                .map(ResponseCommentDto::new)
                .collect(Collectors.toList());

    }
}
