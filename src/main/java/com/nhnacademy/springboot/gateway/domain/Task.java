package com.nhnacademy.springboot.gateway.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class Task {
    private Long id;
    private String name;
    private TaskStatus taskStatus;
    private String mileStone;
    private LocalDateTime createdAt;
    @JsonProperty("admin_id")
    private Long adminId;
    private String content;
    private List<Tag> tags;
}
