package com.example.redis.practice.worker;

import com.example.redis.practice.service.TaskService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

@Component
@RequiredArgsConstructor
@Slf4j
public class ExecutorServiceTaskWorker {

    private final RedisTemplate<String, Object> redisTemplate;
    private final TaskService taskService;
    private final ExecutorService executorService = createExecutorService();

    private ExecutorService createExecutorService() {
        ThreadFactory namedThreadFactory = new ThreadFactory() {
            private final AtomicInteger counter = new AtomicInteger(0);

            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r, "Thread-" + counter.getAndIncrement());
            }
        };
        return Executors.newFixedThreadPool(2, namedThreadFactory);
    }

    public void startWorker() {
        Runnable workerTask = () -> {
            while (true) {
                String threadName = Thread.currentThread().getName();
                taskService.updateThreadStatus(threadName, "Thread : " + threadName + ", status : Idle");
                log.info("Ready to Start!");

                String task = (String) redisTemplate.opsForList().rightPop("task_queue", Duration.ofSeconds(0));
                log.info("Before Start!");
                if (task != null) {
                    log.info("Processing task: " + task + " by " + threadName);
                    taskService.updateThreadStatus(threadName, "Thread : " + threadName + ", status : Processing " + task);

                    try {
                        // 작업 처리 로직 (10초 지연)
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                    log.info("Finished task: " + task + " by " + threadName);
                    taskService.updateThreadStatus(threadName, "Thread : " + threadName + ", status : Finish");
                }
            }
        };

        // 두 개의 작업을 스레드 풀에 제출
        executorService.submit(workerTask);
        executorService.submit(workerTask);
    }

    @PostConstruct
    public void init() {
        startWorker();
    }
}
