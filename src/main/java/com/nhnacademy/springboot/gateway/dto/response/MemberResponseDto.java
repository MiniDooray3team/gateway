package com.nhnacademy.springboot.gateway.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberResponseDto {
    private Long id;
    private String memberId;
    private String email;
}
