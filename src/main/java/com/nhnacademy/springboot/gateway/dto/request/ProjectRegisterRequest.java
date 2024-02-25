package com.nhnacademy.springboot.gateway.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class ProjectRegisterRequest {

    private static final int DEFAULT_PROJECT_STATUS_ID = 1;

    String name;
    int projectStatusId = DEFAULT_PROJECT_STATUS_ID;

}
