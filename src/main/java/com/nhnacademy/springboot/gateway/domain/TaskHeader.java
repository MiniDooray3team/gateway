package com.nhnacademy.springboot.gateway.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TaskHeader {

    private Long taskId;
    private String taskName;
    private String taskStatus;
    private String taskMileStone;
    //todo 주석해제
   // private List<Tag> taskTags;

}
