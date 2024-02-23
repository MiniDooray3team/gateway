package com.nhnacademy.springboot.gateway.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
// todo 어노테이션 삭제
public class Task {
    private Long id;
    private String name;
    private String status;
    private String mileStone;
    private LocalDateTime createdAt;
    private String content;
    private List<Tag> tags;
}
