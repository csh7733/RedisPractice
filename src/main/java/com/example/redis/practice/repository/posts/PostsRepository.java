package com.example.redis.practice.repository.posts;

import com.example.redis.practice.domain.LeaderboardEntity;
import com.example.redis.practice.domain.Posts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostsRepository extends JpaRepository<Posts, Long> {
    Optional<Posts> findByTitle(String title);
}