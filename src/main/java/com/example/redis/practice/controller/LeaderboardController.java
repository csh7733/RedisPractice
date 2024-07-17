package com.example.redis.practice.controller;

import com.example.redis.practice.dto.ResponseLeaderboardDto;
import com.example.redis.practice.service.LeaderboardService;
import com.example.redis.practice.service.RedisService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

import static com.example.redis.practice.DateConst.leaderboardKey;

@RestController
@RequestMapping("/api/leaderboard")
@RequiredArgsConstructor
public class LeaderboardController {

    private final LeaderboardService leaderboardService;

    @GetMapping("/top")
    public List<ResponseLeaderboardDto> getTopScores() {
        return leaderboardService.getTopScores(leaderboardKey, 5);
    }

    @GetMapping("/top2")
    public List<ResponseLeaderboardDto> getTopScores2(@RequestParam String key) {
        return leaderboardService.getTopScores(key, 5);
    }
}
