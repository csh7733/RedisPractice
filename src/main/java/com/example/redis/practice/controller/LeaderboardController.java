package com.example.redis.practice.controller;

import com.example.redis.practice.dto.ResponseLeaderboardDto;
import com.example.redis.practice.service.RedisService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

import static com.example.redis.practice.DateConst.leaderboardKey;

@RestController
@RequestMapping("/api/leaderboard")
@RequiredArgsConstructor
public class LeaderboardController {

    private final RedisService redisService;

    @GetMapping("/top")
    public List<ResponseLeaderboardDto> getTopScores() {
        return redisService.getTopScores(leaderboardKey, 5);
    }
}
