package com.nhnacademy.springboot.gateway.adaptor;

import com.nhnacademy.springboot.gateway.dto.MemberProfileDto;
import com.nhnacademy.springboot.gateway.dto.request.LoginRequestDto;
import com.nhnacademy.springboot.gateway.dto.request.MemberRegisterRequest;
import com.nhnacademy.springboot.gateway.dto.response.MemberResponseDto;

public interface AccountAdapter {
    MemberResponseDto login(LoginRequestDto loginRequestDto);
    void register(MemberRegisterRequest memberRegisterRequest);

    void changeStatus(String status);

    MemberProfileDto getMemberProfile();
}
