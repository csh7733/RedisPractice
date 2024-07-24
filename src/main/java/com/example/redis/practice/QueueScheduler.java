package com.example.redis.practice;

import com.example.redis.practice.dto.ResponseCommentDto;
import com.example.redis.practice.repository.posts.CommentRepository;
import com.example.redis.practice.service.QueueService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class QueueScheduler {

    private final QueueService queueService;

    private final CommentRepository commentRepository;

    @Scheduled(fixedRate = 30000)
    public void flushCommentsToDB() {
        List<ResponseCommentDto> commentQueue = queueService.getAllCommentsFromQueue();
        for (ResponseCommentDto commentDto : commentQueue) {
            log.info("save to DB : comment = {}",commentDto.toString());
            commentRepository.save(commentDto.toEntity());
        }
    }
}
