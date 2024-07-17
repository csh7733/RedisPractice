package com.example.redis.practice.service;

import com.example.redis.practice.domain.LeaderboardEntity;
import com.example.redis.practice.dto.ResponseLeaderboardDto;
import com.example.redis.practice.repository.leaderboard.LeaderboardRepository;
import com.example.redis.practice.repository.redis.RedisRepository;
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
public class LeaderboardService {

    private final LeaderboardRepository leaderboardRepository;
    private final RedisRepository redisRepository;

    public void saveScore(String key, String member, double score) {
        redisRepository.saveZSet(key, member, score);
        saveScoreToDB(key,member, score);
    }

    //원래는 비동기적으로 일정시간마다 저장해야함(테스트용)
    public void saveScoreToDB(String key,String member, double score) {
        LeaderboardEntity entry = LeaderboardEntity.builder()
                .leaderboardKey(key)
                .member(member)
                .score(score)
                .build();
        leaderboardRepository.save(entry);
    }

//    @Cacheable(value = "leaderboard", key = "#key")
//    public List<ResponseLeaderboardDto> getTopScores(String key, int top) {
//        Set<ZSetOperations.TypedTuple<Object>> topScores = redisRepository.getTopScores(key, top);
//        return topScores.stream()
//                .map(ResponseLeaderboardDto::new)
//                .collect(Collectors.toList());
//    }

    @Cacheable(value = "leaderboard", key = "#key", unless = "#result == null || #result.isEmpty()")
    public List<ResponseLeaderboardDto> getTopScores(String key, int top) {
        Set<ZSetOperations.TypedTuple<Object>> topScores = redisRepository.getTopScores(key, top);

        return topScores.stream()
                .map(ResponseLeaderboardDto::new)
                .collect(Collectors.toList());
    }
}
