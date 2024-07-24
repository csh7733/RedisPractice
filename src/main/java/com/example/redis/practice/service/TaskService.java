package com.example.redis.practice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final RedisTemplate<String, Object> redisTemplate;

    public void addTask(String task) {
        redisTemplate.opsForList().leftPush("task_queue", task);
    }

    public void updateThreadStatus(String threadName, String status) {
        redisTemplate.opsForValue().set("thread_status:" + threadName, status);
    }

    public String getThreadStatus(String threadName) {
        return (String) redisTemplate.opsForValue().get("thread_status:" + threadName);
    }


    public Map<String, String> getAllThreadStatuses() {
        Map<String, String> statuses = new HashMap<>();
        statuses.put("Thread-0", getThreadStatus("Thread-0"));
        statuses.put("Thread-1", getThreadStatus("Thread-1"));
        return statuses;
    }
}
