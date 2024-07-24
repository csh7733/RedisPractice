package com.example.redis.practice.controller;

import com.example.redis.practice.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @PostMapping("/add")
    public String addTask(@RequestParam String task) {
        taskService.addTask(task);
        return "Task added: " + task;
    }

    @GetMapping("/status")
    public Map<String, String> getAllThreadStatuses() {
        return taskService.getAllThreadStatuses();
    }
}
