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


}
