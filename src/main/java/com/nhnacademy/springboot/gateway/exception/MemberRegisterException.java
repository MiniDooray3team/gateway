package com.nhnacademy.springboot.gateway.exception;

public class MemberRegisterException extends RuntimeException {
    public MemberRegisterException(String message) {
        super(message);
    }
}
