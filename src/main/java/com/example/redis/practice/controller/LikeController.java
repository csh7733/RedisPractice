package com.example.redis.practice.controller;

import com.example.redis.practice.dto.LikeResponseDto;
import com.example.redis.practice.dto.ResponseLikeRankDto;
import com.example.redis.practice.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/likes")
public class LikeController {

    private final LikeService likeService;

    @PostMapping("/like")
    public String likePost(@RequestParam String postId, @RequestParam String member) {
        likeService.likePost(postId, member);
        return "Liked post " + postId + " by " + member;
    }

    @PostMapping("/unlike")
    public String unlikePost(@RequestParam String postId, @RequestParam String member) {
        likeService.unlikePost(postId, member);
        return "Unliked post " + postId + " by " + member;
    }

    @GetMapping("/{postId}")
    public LikeResponseDto getLikes(@PathVariable String postId) {
        return likeService.getLikes(postId);
    }

    @GetMapping("/{postId}/count")
    public Long getLikeCount(@PathVariable String postId) {
        return likeService.getLikeCount(postId);
    }

    @GetMapping("/top")
    public List<ResponseLikeRankDto> getTopScores(@RequestParam int top) {
        return likeService.getTopScores(top);
    }
}
