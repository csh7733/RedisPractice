package com.example.redis.practice.service;

import com.example.redis.practice.dto.LikeResponseDto;
import com.example.redis.practice.dto.ResponseLeaderboardDto;
import com.example.redis.practice.dto.ResponseLikeRankDto;
import com.example.redis.practice.repository.redis.LikeRepository;
import com.example.redis.practice.repository.redis.RedisRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final LikeRepository likeRepository;
    private final RedisRepository redisRepository;

    private String getLikeKey(String postId) {
        return "post:" + postId + ":likes";
    }
    private String getRankKey() {
        return "likesRank";
    }


    public void likePost(String postId, String member) {
        likeRepository.likePost(getLikeKey(postId), member);
        Long likeCount = getLikeCount(postId);
        likeRepository.updateLikeScore(postId, likeCount);
    }

    public void unlikePost(String postId, String member) {
        likeRepository.unlikePost(getLikeKey(postId), member);
        Long likeCount = getLikeCount(postId);
        likeRepository.updateLikeScore(postId, likeCount);
    }

    public LikeResponseDto getLikes(String postId) {
        Set<String> members = likeRepository.getLikes(getLikeKey(postId));
        Long likeCount = likeRepository.getLikeCount(getLikeKey(postId));
        return new LikeResponseDto(postId, likeCount, members);
    }

    public boolean isLiked(String postId, String member) {
        return likeRepository.isLiked(getLikeKey(postId), member);
    }

    public Long getLikeCount(String postId) {
        return likeRepository.getLikeCount(getLikeKey(postId));
    }

    public List<ResponseLikeRankDto> getTopScores(int top) {
        Set<ZSetOperations.TypedTuple<Object>> topScores = redisRepository.getTopScores(getRankKey(), top);

        return topScores.stream()
                .map(ResponseLikeRankDto::new)
                .collect(Collectors.toList());
    }
}
