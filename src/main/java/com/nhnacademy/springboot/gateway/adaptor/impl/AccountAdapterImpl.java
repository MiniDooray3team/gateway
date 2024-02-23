package com.nhnacademy.springboot.gateway.adaptor.impl;

import com.nhnacademy.springboot.gateway.adaptor.AccountAdapter;
import com.nhnacademy.springboot.gateway.config.AccountProperties;
import com.nhnacademy.springboot.gateway.dto.request.LoginRequestDto;
import com.nhnacademy.springboot.gateway.dto.request.MemberRegisterRequest;
import com.nhnacademy.springboot.gateway.dto.response.MemberResponseDto;
import com.nhnacademy.springboot.gateway.exception.LoginFailException;
import com.nhnacademy.springboot.gateway.exception.MemberRegisterException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@Component
@RequiredArgsConstructor
public class AccountAdapterImpl implements AccountAdapter {

    private final RestTemplate restTemplate;

    private final AccountProperties accountProperties;


    @Override
    public MemberResponseDto login(LoginRequestDto loginRequestDto) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<LoginRequestDto> requestEntity = new HttpEntity<>(loginRequestDto, httpHeaders);
        ResponseEntity<MemberResponseDto> exchange = restTemplate.exchange(accountProperties.getAddress() + "/account/login", HttpMethod.POST, requestEntity, MemberResponseDto.class);

        if (exchange.getStatusCode() != HttpStatus.OK) {
            throw new LoginFailException();
        }

        return exchange.getBody();
    }

    @Override
    public void register(MemberRegisterRequest memberRegisterRequest) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<MemberRegisterRequest> requestEntity = new HttpEntity<>(memberRegisterRequest, httpHeaders);

        ResponseEntity<MemberResponseDto> exchange = restTemplate.exchange(
                accountProperties.getAddress() + "/account/members"
                , HttpMethod.POST
                , requestEntity
                , MemberResponseDto.class);

        if (exchange.getStatusCode() == HttpStatus.CREATED) {
            return;
        }
        // FIXME : 검증작업은 GATEWAY에서 처리? ACCOUNT API에서 처리?
        throw new MemberRegisterException("회원가입 실패");
    }

    @Override
    public void changeStatus() {

    }

}
