package com.nhnacademy.springboot.gateway.dto.request;

import lombok.Getter;

import java.util.List;

@Getter
public class TaskUpdateRequest {
       private Long id;
       private String name;
       //private Long milestoneId;
       private String content;
      // private Long taskStatusId;
       //private List<Long> tags;
}
