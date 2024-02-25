package com.nhnacademy.springboot.gateway.domain;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Project {

    Long id;
    LocalDateTime createdAt;
    String name;
    Long projectStatusId;
    String admin_id;


}
