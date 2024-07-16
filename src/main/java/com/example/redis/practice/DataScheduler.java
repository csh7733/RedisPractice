package com.example.redis.practice;

import com.example.redis.practice.service.RedisService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import static com.example.redis.practice.DateConst.leaderboardKey;

@Component
@RequiredArgsConstructor
public class DataScheduler {

    private final RedisService redisService;
    private static final Random random = new Random();

    @Scheduled(fixedRate = 5000) // 5초마다 실행
    public void generateData() {
        String member = "player" + (random.nextInt(100) + 1);
        double score = random.nextDouble() * 1000;
        redisService.saveScore(leaderboardKey, member, score);
    }
}