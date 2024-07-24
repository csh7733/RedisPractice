package com.example.redis.practice.controller;

import com.example.redis.practice.dto.RequestStringDto;
import com.example.redis.practice.dto.ResponseCommentDto;
import com.example.redis.practice.dto.ResponsePostsDto;
import com.example.redis.practice.service.CommentService;
import com.example.redis.practice.service.PostsService;
import com.example.redis.practice.service.RedisService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class DataController {

    private final RedisService redisService;
    private final PostsService postsService;
    private final CommentService commentService;

    @PostMapping("/string")
    public String setString(@RequestBody RequestStringDto requestStringDto) {
        String key = requestStringDto.getKey();
        String value = requestStringDto.getValue();
        redisService.saveString(key, value);
        return "Set key=" + key + ", value=" + value;
    }

    @GetMapping("/string")
    public String getString(@RequestParam String key) {
        return "Get key=" + key + ", value=" + redisService.getString(key);
    }


    @PostMapping("/hash")
    public String setHash(@RequestParam String key, @RequestBody Map<String, Object> hash) {
        redisService.saveHash(key, hash);
        return "Set hash key=" + key + ", value=" + hash;
    }

    @GetMapping("/hash")
    public Map<Object, Object> getHash(@RequestParam String key) {
        return redisService.getHash(key);
    }

    @PostMapping("/list")
    public String setList(@RequestParam String key, @RequestBody List<String> list) {
        redisService.saveList(key, list);
        return "Set list key=" + key + ", value=" + list;
    }

    @GetMapping("/list")
    public List<Object> getList(@RequestParam String key) {
        return redisService.getList(key);
    }

    @PostMapping("/set")
    public String setSet(@RequestParam String key, @RequestBody Set<String> set) {
        redisService.saveSet(key, set);
        return "Set set key=" + key + ", value=" + set;
    }

    @GetMapping("/set")
    public Set<Object> getSet(@RequestParam String key) {
        return redisService.getSet(key);
    }

    @PostMapping("/zset")
    public String setZSet(@RequestParam String key, @RequestParam String value, @RequestParam double score) {
        redisService.saveZSet(key, value, score);
        return "Set ZSet key=" + key + ", value=" + value + ", score=" + score;
    }

    @GetMapping("/zset")
    public Set<Object> getZSet(@RequestParam String key, @RequestParam double min, @RequestParam double max) {
        return redisService.getZSet(key, min, max);
    }

    @GetMapping("/notice")
    public List<ResponsePostsDto> getNotice() {
        return postsService.getNotice();
    }

    @GetMapping("/notice/{id}")
    public List<ResponsePostsDto> getNoticeById(@PathVariable Long id) {
        return postsService.addNotice(id);
    }

    @GetMapping("/notice/all")
    public List<ResponsePostsDto> getNoticeFromDB() {
        return postsService.getNoticeFromDB();
    }

    @GetMapping("/comment")
    public List<ResponseCommentDto> getCommnetFromCache() {
        return commentService.getCommentsFromCache();
    }

    @GetMapping("/comment/all")
    public List<ResponseCommentDto> getCommnetFromDB() {
        return commentService.getCommentsFromDB();
    }
}
