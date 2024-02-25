package com.nhnacademy.springboot.gateway.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
@Getter
@AllArgsConstructor
public class MemberRegisterRequest {

    private String memberId;
    private String password;
    private String email;

}
