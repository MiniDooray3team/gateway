package com.nhnacademy.springboot.gateway.advice;

import com.nhnacademy.springboot.gateway.exception.LoginFailException;
import com.nhnacademy.springboot.gateway.exception.MemberRegisterException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice
@Slf4j
public class ControllerAdvices {

    @ExceptionHandler(LoginFailException.class)
    public String loginFail(LoginFailException e, RedirectAttributes attr) {
        log.debug("LoginFailException: {}", e.getMessage());
        attr.addFlashAttribute("message", e.getMessage());
        return "redirect:/account/login";
    }

    @ExceptionHandler(MemberRegisterException.class)
    public String memberRegisterFail(MemberRegisterException e, RedirectAttributes attr) {
        log.debug("MemberRegisterException: {}", e.getMessage());
        attr.addFlashAttribute("message", e.getMessage());
        return "redirect:/account/register";
    }

}
