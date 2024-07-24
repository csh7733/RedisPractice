package com.example.redis.practice.service;

import com.example.redis.practice.dto.ResponseCommentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QueueService {

    private final RedisTemplate<String, Object> redisTemplate;

    private static final String COMMENT_QUEUE = "commentQueue";

    public void addCommentToQueue(ResponseCommentDto comment) {
        redisTemplate.opsForList().leftPush(COMMENT_QUEUE, comment);
    }

    public List<ResponseCommentDto> getAllCommentsFromQueue() {
        List<Object> commentObjects = redisTemplate.opsForList().range(COMMENT_QUEUE, 0, -1);
        List<ResponseCommentDto> comments = commentObjects.stream()
                .filter(obj -> obj instanceof ResponseCommentDto)
                .map(obj -> (ResponseCommentDto) obj)
                .collect(Collectors.toList());
        redisTemplate.opsForList().trim(COMMENT_QUEUE, 1, 0);
        return comments;
    }
}
