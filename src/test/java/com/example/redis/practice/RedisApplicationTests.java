package com.example.redis.practice;

import com.example.redis.practice.service.RedisService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class RedisApplicationTests {

    @Autowired
    private RedisService redisService;

    @Test
    public void testRedis() {
//        redisService.saveData("csh", "Hello World!");
//        String value = redisService.getData("csh");
//        assertThat(value).isEqualTo("Hello World!");
    }
}
