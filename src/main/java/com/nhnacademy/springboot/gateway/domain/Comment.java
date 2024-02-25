package com.nhnacademy.springboot.gateway.domain;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Comment {
    private Long id;
    private String writerId;
    private String content;
    private LocalDateTime createAt;
    private LocalDateTime updatedAt;

    // todo 삭제예정
    public static Comment testComment() {
        Comment comment = new Comment();
        comment.id = 1L;
        comment.writerId = "test";
        comment.content = "test";
        comment.createAt = LocalDateTime.now();
        comment.updatedAt = LocalDateTime.now();
        return comment;
    }


}
