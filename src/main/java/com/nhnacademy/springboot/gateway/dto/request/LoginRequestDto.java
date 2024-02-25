package com.nhnacademy.springboot.gateway.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginRequestDto {

    String memberId;

    String password;



}
