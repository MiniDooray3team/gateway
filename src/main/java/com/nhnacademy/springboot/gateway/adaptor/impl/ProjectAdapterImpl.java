package com.nhnacademy.springboot.gateway.adaptor.impl;

import com.nhnacademy.springboot.gateway.adaptor.ProjectAdapter;
import com.nhnacademy.springboot.gateway.config.TaskProperties;
import com.nhnacademy.springboot.gateway.domain.*;
import com.nhnacademy.springboot.gateway.dto.request.ProjectRegisterRequest;
import com.nhnacademy.springboot.gateway.dto.request.TaskRegisterRequest;
import com.nhnacademy.springboot.gateway.dto.request.TaskUpdateRequest;
import com.nhnacademy.springboot.gateway.dto.response.ProjectResponseDto;
import com.nhnacademy.springboot.gateway.thread.MemberSerialIdHolder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Component
@Slf4j
@RequiredArgsConstructor
public class ProjectAdapterImpl implements ProjectAdapter {


    private static final String AUTH_HEADER = "MEMBER-SERIAL-ID";

    private final RestTemplate restTemplate;

    private final TaskProperties taskProperties;



    @Override
    public List<ProjectResponseDto> getProjects() {
        String memberSerialId = MemberSerialIdHolder.getSerialId().toString();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.add(AUTH_HEADER, memberSerialId);
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<Void> requestEntity = new HttpEntity<>(httpHeaders);

        ResponseEntity<List<ProjectResponseDto>> exchange = restTemplate.exchange(
                taskProperties.getAddress() + "/project",
                HttpMethod.GET,
                requestEntity,
                new ParameterizedTypeReference<>() {
                }
        );
        return exchange.getBody();
    }

    @Override
    public Project getProject(Long projectId){
        HttpHeaders httpHeaders = getDefaultHeaders();
        HttpEntity<Void> requestEntity = new HttpEntity<>(httpHeaders);

        ResponseEntity<Project> exchange = restTemplate.exchange(
                taskProperties.getAddress() + "/project/" + projectId,
                HttpMethod.GET,
                requestEntity,
                new ParameterizedTypeReference<>() {
                }
        );
        return exchange.getBody();
    }

    @Override
    public List<TaskHeader> getProjectTaskHeaders(Long projectId){
        HttpHeaders httpHeaders = getDefaultHeaders();


        HttpEntity<Void> requestEntity = new HttpEntity<>(httpHeaders);

        ResponseEntity<List<TaskHeader>> exchange = restTemplate.exchange(
                taskProperties.getAddress() + "/project/" + projectId + "/task",
                HttpMethod.GET,
                requestEntity,
                new ParameterizedTypeReference<>() {
                }
        );
        return exchange.getBody();
      //  return List.of(new TaskHeader(1L,"태스크1", TaskStatusDto.of(1, "할 일"),"마일스톤"),new TaskHeader(2L,"태스크2",TaskStatusDto.of(2, "진행중"),"마일스톤"),new TaskHeader(3L,"태스크3",TaskStatusDto.of(3, "종료"),"마일스톤"));
    }

    @Override
    public Task getProjectTask(Long projectId, Long taskId) {
        String memberSerialId = MemberSerialIdHolder.getSerialId().toString();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.add(AUTH_HEADER, memberSerialId);
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<Void> requestEntity = new HttpEntity<>(httpHeaders);

        ResponseEntity<Task> exchange = restTemplate.exchange(
                taskProperties.getAddress() + "/project/" + projectId + "/task/" + taskId,
                HttpMethod.GET,
                requestEntity,
                new ParameterizedTypeReference<>() {
                }
        );
        return exchange.getBody();
      //  return new Task(1L,"태스크1","할 일","마일스톤", LocalDateTime.now(),"할 일을 해야합니다.", List.of(new Tag(1L,"태그1"),new Tag(2L, "태그2")));
    }

    @Override
    public List<Comment> getTaskComments(Long projectId ,Long taskId) {
//        HttpHeaders httpHeaders = getDefaultHeaders();
//        HttpEntity<Void> requestEntity = new HttpEntity<>(httpHeaders);
//
//        ResponseEntity<List<Comment>> exchange = restTemplate.exchange(
//                taskProperties.getAddress() + "/projects/"+ projectId+ "tasks" + taskId + "/comments",
//                HttpMethod.GET,
//                requestEntity,
//                new ParameterizedTypeReference<>() {
//                }
//        );
//
//        if (exchange.getStatusCode() == HttpStatus.OK) {
//            return exchange.getBody();
//        }
//        throw new RuntimeException("태스크 댓글 조회 실패");
        return List.of(Comment.testComment(), Comment.testComment(), Comment.testComment());
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
        log.trace("requestEntity: {}", requestEntity);
        ResponseEntity<Void> exchange = restTemplate.exchange(
                taskProperties.getAddress() + "/project",
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

        HttpHeaders httpHeaders = getDefaultHeaders();
        HttpEntity<TaskRegisterRequest> requestEntity = new HttpEntity<>(taskRegisterRequest, httpHeaders);
        System.out.println(taskRegisterRequest.getName());
        ResponseEntity<Void> exchange = restTemplate.exchange(
                taskProperties.getAddress() + "/project/" + projectId + "/task",
                HttpMethod.POST,
                requestEntity,
                Void.class
        );

        if (exchange.getStatusCode() == HttpStatus.CREATED) {
            return;
        }
        throw new RuntimeException("태스크 생성 실패");

    }

    @Override
    public void saveTaskComment(Long projectId, Long taskId, String comment) {
        HttpHeaders httpHeaders = getDefaultHeaders();
        HttpEntity<Map<String, String>> requestEntity = new HttpEntity<>(Map.of("comment", comment), httpHeaders);

        ResponseEntity<Void> exchange = restTemplate.exchange(
                taskProperties.getAddress() + "/projects/" + projectId + "/tasks/" + taskId + "/comments",
                HttpMethod.POST,
                requestEntity,
                Void.class
        );

        if (exchange.getStatusCode() == HttpStatus.CREATED) {
            return;
        }
        throw new RuntimeException("태스크 댓글 저장 실패");

    }

    @Override
    public void createProjectTag(Long projectId, String tagName) {
        HttpHeaders httpHeaders = getDefaultHeaders();
        HttpEntity<Map<String, String>> requestEntity = new HttpEntity<>(Map.of("tagName", tagName), httpHeaders);

        ResponseEntity<Void> exchange = restTemplate.exchange(
                taskProperties.getAddress() + "/projects/" + projectId + "/tags",
                HttpMethod.POST,
                requestEntity,
                Void.class
        );

        if (exchange.getStatusCode() == HttpStatus.CREATED) {
            return;
        }
        throw new RuntimeException("프로젝트 태그 생성 실패");
    }
    @Override
    public void createProjectMileStone(Long projectId, String mileStoneName) {
        HttpHeaders httpHeaders = getDefaultHeaders();
        HttpEntity<Map<String, String>> requestEntity = new HttpEntity<>(Map.of("mileStoneName", mileStoneName), httpHeaders);

        ResponseEntity<Void> exchange = restTemplate.exchange(
                taskProperties.getAddress() + "/projects/" + projectId + "/mileStones",
                HttpMethod.POST,
                requestEntity,
                Void.class
        );

        if (exchange.getStatusCode() == HttpStatus.CREATED) {
            return;
        }
        throw new RuntimeException("프로젝트 마일스톤 생성 실패");

    }

    @Override
    public void deleteProject(Long projectId) {
        HttpHeaders httpHeaders = getDefaultHeaders();
        HttpEntity<Void> requestEntity = new HttpEntity<>(httpHeaders);

        ResponseEntity<Void> exchange = restTemplate.exchange(
                taskProperties.getAddress() + "/projects/" + projectId,
                HttpMethod.DELETE,
                requestEntity,
                Void.class
        );

        if (exchange.getStatusCode() == HttpStatus.NO_CONTENT) {
            return;
        }
        throw new RuntimeException("프로젝트 삭제 실패");
    }

    @Override
    public void updateProjectTask(Long projectId, Long taskId, TaskUpdateRequest taskRegisterRequest) {
        HttpHeaders httpHeaders = getDefaultHeaders();
        HttpEntity<TaskUpdateRequest> requestEntity = new HttpEntity<>(taskRegisterRequest, httpHeaders);

        ResponseEntity<Void> exchange = restTemplate.exchange(
          //      taskProperties.getAddress() + "/project/" + projectId + "/task/" + taskId,
              taskProperties.getAddress() + "/project/" + projectId + "/task",

                HttpMethod.PUT,
                requestEntity,
                Void.class
        );
        if (exchange.getStatusCode() == HttpStatus.OK) {
            return;
        }
        throw new RuntimeException("태스크 수정 실패");
    }

    @Override
    public void deleteProjectTask(Long projectId, Long taskId) {
        HttpHeaders httpHeaders = getDefaultHeaders();
        HttpEntity<Void> requestEntity = new HttpEntity<>(httpHeaders);

        ResponseEntity<Void> exchange = restTemplate.exchange(
                taskProperties.getAddress() + "/projects/" + projectId + "/tasks/" + taskId,
                HttpMethod.DELETE,
                requestEntity,
                Void.class
        );

        if (exchange.getStatusCode() == HttpStatus.NO_CONTENT) {
            return;
        }
        throw new RuntimeException("태스크 삭제 실패");

    }

    @Override
    public List<Tag> getProjectTags(Long projectId) {
        HttpHeaders httpHeaders = getDefaultHeaders();
        HttpEntity<Void> requestEntity = new HttpEntity<>(httpHeaders);

        ResponseEntity<List<Tag>> exchange = restTemplate.exchange(
                taskProperties.getAddress() + "/projects/" + projectId + "/tags",
                HttpMethod.GET,
                requestEntity,
                new ParameterizedTypeReference<>() {
                }
        );
        if (exchange.getStatusCode() == HttpStatus.OK) {
            return exchange.getBody();
        }
        throw new RuntimeException("프로젝트 태그 조회 실패");
    }


    @Override
    public void deleteProjectTag(Long projectId, Long tagId) {
        HttpHeaders httpHeaders = getDefaultHeaders();
        HttpEntity<Void> requestEntity = new HttpEntity<>(httpHeaders);

        ResponseEntity<Void> exchange = restTemplate.exchange(
                taskProperties.getAddress() + "/projects/" + projectId + "/tags/" + tagId,
                HttpMethod.DELETE,
                requestEntity,
                Void.class
        );
        if (exchange.getStatusCode() == HttpStatus.NO_CONTENT) {
            return;
        }
        throw new RuntimeException("프로젝트 태그 삭제 실패");

    }

    @Override
    public void updateProjectTag(Long projectId, Long tagId, String updateTagName) {

    }

    @Override
    public List<MileStone> getProjectMileStones(Long projectId) {
        HttpHeaders httpHeaders = getDefaultHeaders();
        HttpEntity<Void> requestEntity = new HttpEntity<>(httpHeaders);

        ResponseEntity<List<MileStone>> exchange = restTemplate.exchange(
                taskProperties.getAddress() + "/projects/" + projectId + "/mileStones",
                HttpMethod.GET,
                requestEntity,
                new ParameterizedTypeReference<>() {
                }
        );

        if (exchange.getStatusCode() == HttpStatus.OK) {
            return exchange.getBody();
        }
        throw new RuntimeException("프로젝트 마일스톤 조회 실패");
    }



    @Override
    public void deleteProjectMileStone(Long projectId, Long mileStoneId) {

        HttpHeaders httpHeaders = getDefaultHeaders();
        HttpEntity<Void> requestEntity = new HttpEntity<>(httpHeaders);

        ResponseEntity<Void> exchange = restTemplate.exchange(
                taskProperties.getAddress() + "/projects/" + projectId + "/mileStones/" + mileStoneId,
                HttpMethod.DELETE,
                requestEntity,
                Void.class
        );
        if (exchange.getStatusCode() == HttpStatus.NO_CONTENT) {
            return;
        }
        throw new RuntimeException("프로젝트 마일스톤 삭제 실패");
    }

    @Override
    public void updateProjectMileStone(Long projectId, Long mileStoneId, String updateMileStoneName) {

    }

    @Override
    public void registerTaskTag(Long projectId, Long taskId, Long tagId) {
        HttpHeaders httpHeaders = getDefaultHeaders();
        HttpEntity<Map<String, Long>> requestEntity = new HttpEntity<>(Map.of("id" , tagId), httpHeaders);

        ResponseEntity<Void> exchange = restTemplate.exchange(
                taskProperties.getAddress() + "/projects/" + projectId + "/tasks/" + taskId,
                HttpMethod.POST,
                requestEntity,
                Void.class
        );
        if (exchange.getStatusCode() == HttpStatus.CREATED) {
            return;
        }
        throw new RuntimeException("태스크 태그 등록 실패");
    }

    @Override
    public void deleteTaskTag(Long projectId, Long taskId, Long tagId) {
        HttpHeaders httpHeaders = getDefaultHeaders();
        HttpEntity<Void> requestEntity = new HttpEntity<>(httpHeaders);

        ResponseEntity<Void> exchange = restTemplate.exchange(
                taskProperties.getAddress() + "/projects/" + projectId + "/tasks/" + taskId + "/tags/" + tagId,
                HttpMethod.DELETE,
                requestEntity,
                Void.class
        );
        if (exchange.getStatusCode() == HttpStatus.NO_CONTENT) {
            return;
        }
        throw new RuntimeException("태스크 태그 삭제 실패");
    }

    @Override
    public void registerTaskMileStone(Long projectId, Long taskId, Long mileStoneId) {
        HttpHeaders httpHeaders = getDefaultHeaders();
        HttpEntity<Map<String, Long>> requestEntity = new HttpEntity<>(Map.of("id" , mileStoneId), httpHeaders);

        ResponseEntity<Void> exchange = restTemplate.exchange(
                taskProperties.getAddress() + "/projects/" + projectId + "/tasks/" + taskId,
                HttpMethod.POST,
                requestEntity,
                Void.class
        );
        if (exchange.getStatusCode() == HttpStatus.CREATED) {
            return;
        }
        throw new RuntimeException("태스크 마일스톤 등록 실패");

    }

    @Override
    public void deleteTaskMileStone(Long projectId, Long taskId, Long mileStoneId) {
        HttpHeaders httpHeaders = getDefaultHeaders();
        HttpEntity<Void> requestEntity = new HttpEntity<>(httpHeaders);

        ResponseEntity<Void> exchange = restTemplate.exchange(
                taskProperties.getAddress() + "/projects/" + projectId + "/tasks/" + taskId + "/mileStones/" + mileStoneId,
                HttpMethod.DELETE,
                requestEntity,
                Void.class
        );
        if (exchange.getStatusCode() == HttpStatus.NO_CONTENT) {
            return;
        }
        throw new RuntimeException("태스크 마일스톤 삭제 실패");

    }


    @Override
    public void deleteTaskComment(Long projectId, Long taskId, String comment) {
        HttpHeaders httpHeaders = getDefaultHeaders();
        HttpEntity<Void> requestEntity = new HttpEntity<>(httpHeaders);

        ResponseEntity<Void> exchange = restTemplate.exchange(
                taskProperties.getAddress() + "/projects/" + projectId + "/tasks/" + taskId + "/comments/" + comment,
                HttpMethod.DELETE,
                requestEntity,
                Void.class
        );
        if (exchange.getStatusCode() == HttpStatus.NO_CONTENT) {
            return;
        }
        throw new RuntimeException("태스크 댓글 삭제 실패");

    }

    @Override
    public void updateTaskComment(Long projectId, Long taskId, String comment) {

    }

    private HttpHeaders getDefaultHeaders(){
        String memberSerialId = MemberSerialIdHolder.getSerialId().toString();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.add(AUTH_HEADER, memberSerialId);
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));

        return httpHeaders;
    }

}
