
package com.example.redis.practice.dto;

import com.example.redis.practice.domain.Posts;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.ZSetOperations;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponsePostsDto {

    private Long id;
    private String title;
    private String content;

    public ResponsePostsDto(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public ResponsePostsDto(Posts posts) {
        this.id = posts.getId();
        this.title = posts.getTitle();
        this.content = posts.getContent();
    }
}
