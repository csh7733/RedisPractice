
package com.example.redis.practice.dto;

import com.example.redis.practice.domain.Comment;
import com.example.redis.practice.domain.Posts;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseCommentDto {

    private Long id;
    private String author;
    private String content;

    public ResponseCommentDto(String author, String content) {
        this.author = author;
        this.content = content;
    }

    public ResponseCommentDto(Comment comment) {
        this.id = comment.getId();
        this.author = comment.getAuthor();
        this.content = comment.getContent();
    }

    public Comment toEntity(){
        return Comment.builder()
                .author(author)
                .content(content)
                .build();
    }
}
