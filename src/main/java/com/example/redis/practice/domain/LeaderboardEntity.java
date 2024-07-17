package com.example.redis.practice.domain;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class LeaderboardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String leaderboardKey;
    private String member;
    private double score;

    @Builder
    public LeaderboardEntity(String leaderboardKey,String member, double score) {
        this.leaderboardKey = leaderboardKey;
        this.member = member;
        this.score = score;
    }
}
