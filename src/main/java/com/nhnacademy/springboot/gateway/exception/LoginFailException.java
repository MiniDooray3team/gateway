package com.nhnacademy.springboot.gateway.exception;

public class LoginFailException extends RuntimeException{
    public LoginFailException() {
        super("로그인 실패");
    }
}
