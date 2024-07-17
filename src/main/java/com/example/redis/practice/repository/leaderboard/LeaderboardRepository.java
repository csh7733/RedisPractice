package com.example.redis.practice.repository.leaderboard;

import com.example.redis.practice.domain.LeaderboardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeaderboardRepository extends JpaRepository<LeaderboardEntity, Long> {

    @Query(value = "SELECT * FROM leaderboard_entity WHERE leaderboard_key = :key ORDER BY score DESC LIMIT :top", nativeQuery = true)
    List<LeaderboardEntity> findTopNByKey(String key, int top);
}