package com.nhnacademy.springboot.gateway.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class MemberProfileDto {
    private String id;

    private String memberId;

    private String email;

    private MemberStatusDto status;

    private LocalDateTime createdAt;

}
