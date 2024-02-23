package com.nhnacademy.springboot.gateway.adaptor.impl;

import com.nhnacademy.springboot.gateway.adaptor.ProjectAdapter;
import com.nhnacademy.springboot.gateway.config.TaskProperties;
import com.nhnacademy.springboot.gateway.domain.Tag;
import com.nhnacademy.springboot.gateway.domain.Task;
import com.nhnacademy.springboot.gateway.domain.TaskHeader;
import com.nhnacademy.springboot.gateway.dto.request.ProjectRegisterRequest;
import com.nhnacademy.springboot.gateway.dto.request.TaskRegisterRequest;
import com.nhnacademy.springboot.gateway.dto.response.ProjectResponseDto;
import com.nhnacademy.springboot.gateway.thread.MemberSerialIdHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ProjectAdapterImpl implements ProjectAdapter {


    private static final String AUTH_HEADER = "MEMBER-SERIAL-ID";

    private final RestTemplate restTemplate;

    private final TaskProperties taskProperties;



    @Override
    public List<ProjectResponseDto> getProjects() {
        // todo 연결 시 주석 해제
//        String memberSerialId = MemberSerialIdHolder.getSerialId().toString();
//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
//        httpHeaders.add(AUTH_HEADER, memberSerialId);
//        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));
//
//        HttpEntity<Void> requestEntity = new HttpEntity<>(httpHeaders);
//
//        ResponseEntity<List<ProjectResponseDto>> exchange = restTemplate.exchange(
//                taskProperties.getAddress() + "/projects",
//                HttpMethod.GET,
//                requestEntity,
//                new ParameterizedTypeReference<>() {
//                }
//        );
//        return exchange.getBody();
        return List.of(new ProjectResponseDto(1L,"프로젝트1"),new ProjectResponseDto(2L,"프로젝트2"),new ProjectResponseDto(3L,"프로젝트3"));
    }

    @Override
    public List<TaskHeader> getProjectTaskHeaders(Long projectId){
        // todo 연결 시 주석 해제
//        String memberSerialId = MemberSerialIdHolder.getSerialId().toString();
//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
//        httpHeaders.add(AUTH_HEADER, memberSerialId);
//        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));
//
//        HttpEntity<Void> requestEntity = new HttpEntity<>(httpHeaders);
//
//        ResponseEntity<List<TaskHeader>> exchange = restTemplate.exchange(
//                taskProperties.getAddress() + "/projects/" + projectId + "/tasks",
//                HttpMethod.GET,
//                requestEntity,
//                new ParameterizedTypeReference<>() {
//                }
//        );
//        return exchange.getBody();
        return List.of(new TaskHeader(1L,"태스크1","할 일","마일스톤"),new TaskHeader(2L,"태스크2","할 일","마일스톤"),new TaskHeader(3L,"태스크3","할 일","마일스톤"));
    }

    @Override
    public Task getProjectTask(Long projectId, Long taskId) {
        // todo 연결 시 주석 해제
//        String memberSerialId = MemberSerialIdHolder.getSerialId().toString();
//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
//        httpHeaders.add(AUTH_HEADER, memberSerialId);
//        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));
//
//        HttpEntity<Void> requestEntity = new HttpEntity<>(httpHeaders);
//
//        ResponseEntity<Task> exchange = restTemplate.exchange(
//                taskProperties.getAddress() + "/projects/" + projectId + "/tasks/" + taskId,
//                HttpMethod.GET,
//                requestEntity,
//                new ParameterizedTypeReference<>() {
//                }
//        );
//        return exchange.getBody();
        return new Task(1L,"태스크1","할 일","마일스톤", LocalDateTime.now(),"할 일을 해야합니다.", List.of(new Tag(1L,"태그1"),new Tag(2L, "태그2")));
    }


    // 프로젝트 생성 요청
    @Override
    public void createProject(ProjectRegisterRequest projectRegisterRequest) {
        String memberSerialId = MemberSerialIdHolder.getSerialId().toString();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.add(AUTH_HEADER, memberSerialId);
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<ProjectRegisterRequest> requestEntity = new HttpEntity<>(projectRegisterRequest, httpHeaders);

        ResponseEntity<Void> exchange = restTemplate.exchange(
                taskProperties.getAddress() + "/projects",
                HttpMethod.POST,
                requestEntity,
                new ParameterizedTypeReference<>() {
                }
        );

        if (exchange.getStatusCode() == HttpStatus.CREATED) {
            return;
        }
        throw new RuntimeException("프로젝트 생성 실패");
    }



    @Override
    public void createProjectTask(Long projectId, TaskRegisterRequest taskRegisterRequest) {
        String memberSerialId = MemberSerialIdHolder.getSerialId().toString();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.add(AUTH_HEADER, memberSerialId);
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<TaskRegisterRequest> requestEntity = new HttpEntity<>(taskRegisterRequest, httpHeaders);

        ResponseEntity<Void> exchange = restTemplate.exchange(
                taskProperties.getAddress() + "/projects/" + projectId + "/tasks",
                HttpMethod.POST,
                requestEntity,
                Void.class
        );

        if (exchange.getStatusCode() == HttpStatus.CREATED) {
            return;
        }
        throw new RuntimeException("태스크 생성 실패");

    }

}
