package com.nhnacademy.springboot.gateway.intercepter;

import com.nhnacademy.springboot.gateway.dto.response.MemberResponseDto;
import com.nhnacademy.springboot.gateway.thread.MemberSerialIdHolder;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginCheckInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        MemberResponseDto member = (MemberResponseDto) session.getAttribute("member");
        if (member == null) {
            response.sendRedirect("/account/login");
            return false;
        }
        MemberSerialIdHolder.setSerialId(member.getId());
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)  {
        MemberSerialIdHolder.clear();
    }
}
