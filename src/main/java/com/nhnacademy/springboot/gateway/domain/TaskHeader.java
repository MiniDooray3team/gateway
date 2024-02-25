package com.nhnacademy.springboot.gateway.domain;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class TaskHeader {

    private Long id;
    private String name;
    private TaskStatus taskStatus;
    private LocalDateTime createdAt;
    private String mileStone;
    private List<Tag> tags;
}
