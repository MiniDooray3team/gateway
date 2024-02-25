package com.nhnacademy.springboot.gateway.adaptor;

import com.nhnacademy.springboot.gateway.domain.*;
import com.nhnacademy.springboot.gateway.dto.request.ProjectRegisterRequest;
import com.nhnacademy.springboot.gateway.dto.request.TaskRegisterRequest;
import com.nhnacademy.springboot.gateway.dto.request.TaskUpdateRequest;
import com.nhnacademy.springboot.gateway.dto.response.ProjectResponseDto;

import java.util.List;

public interface ProjectAdapter {

      List<ProjectResponseDto> getProjects();

      Project getProject(Long projectId);
      void createProject(ProjectRegisterRequest projectRegisterRequest);

      void deleteProject(Long projectId);




      // 프로젝트의 태스크 미리보기 리스트
      List<TaskHeader> getProjectTaskHeaders(Long projectId);
      // 프로젝트의 태스크
      Task getProjectTask(Long projectId, Long taskId);
      void createProjectTask(Long projectId, TaskRegisterRequest taskRegisterRequest);
      void updateProjectTask(Long projectId, Long taskId, TaskUpdateRequest taskRegisterRequest);
      void deleteProjectTask(Long projectId, Long taskId);


      //  프로젝트의 태그 리스트
      List<Tag> getProjectTags(Long projectId);
      void createProjectTag(Long projectId, String tagName);
      void deleteProjectTag(Long projectId, Long tagId);
      void updateProjectTag(Long projectId, Long tagId, String updateTagName);

      // 프로젝트의 마일스톤 리스트
      List<MileStone> getProjectMileStones(Long projectId);
      void createProjectMileStone(Long projectId, String mileStoneName);
      void deleteProjectMileStone(Long projectId, Long mileStoneId);
      void updateProjectMileStone(Long projectId, Long mileStoneId, String updateMileStoneName);

      //태스크의 태그
      void registerTaskTag(Long projectId, Long taskId, Long tagId);
      void deleteTaskTag(Long projectId, Long taskId, Long tagId);

      //태스크 마일스톤
      void registerTaskMileStone(Long projectId, Long taskId, Long mileStoneId);
      void deleteTaskMileStone(Long projectId, Long taskId,  Long mileStoneId);

      //태스크의 댓글
      List<Comment> getTaskComments(Long projectId, Long taskId);
      void saveTaskComment(Long projectId, Long taskId, String comment);
      void deleteTaskComment(Long projectId, Long taskId, String comment);
      void updateTaskComment(Long projectId, Long taskId, String comment);



}
