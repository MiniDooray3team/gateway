package com.nhnacademy.springboot.gateway.adaptor;

import com.nhnacademy.springboot.gateway.domain.Task;
import com.nhnacademy.springboot.gateway.domain.TaskHeader;
import com.nhnacademy.springboot.gateway.dto.request.ProjectRegisterRequest;
import com.nhnacademy.springboot.gateway.dto.request.TaskRegisterRequest;
import com.nhnacademy.springboot.gateway.dto.response.ProjectResponseDto;

import java.util.List;

public interface ProjectAdapter {

      void createProject(ProjectRegisterRequest projectRegisterRequest);
//    public void updateProject(String name, String description, String startDate, String endDate, String status);
//    public void deleteProject(String name);
//    public void getProject(String name);
      List<ProjectResponseDto> getProjects();
//    public void getProjectStatuses();
//    public void getProjectStatus(String name);
//    public void createProjectStatus(String name);
//    public void updateProjectStatus(String name);
//    public void deleteProjectStatus(String name);
      List<TaskHeader> getProjectTaskHeaders(Long projectId);
      Task getProjectTask(Long projectId, Long taskId);
      void createProjectTask(Long projectId, TaskRegisterRequest taskRegisterRequest);
//    public void updateProjectTask(String name, String taskName, String description, String startDate, String endDate, String status);
//    public void deleteProjectTask(String name, String taskName);
//    public void getProjectTaskStatuses();
//    public void getProjectTaskStatus(String name);
//    public void createProjectTaskStatus(String name);
//    public void updateProjectTaskStatus(String name);
//    public void deleteProjectTaskStatus(String name);
//    public void getProjectTaskTags(String name);
//    public void getProjectTaskTag(String name, String tagName);
//    public void createProjectTaskTag(String name, String tagName);
//    public void deleteProjectTaskTag(String name, String tagName);
//    public void getProjectTaskComments(String name);
//    public void getProjectTaskComment(String name, String comment);
//    public void createProjectTaskComment(String name, String comment);
//    public void deleteProjectTaskComment(String name, String comment);
//    public void getProjectTaskAttachments(String name);
//    public void getProjectTaskAttachment(String name, String attachment);
//    public void createProjectTaskAttachment(String name, String attachment);
//    public void deleteProjectTaskAttachment(String name, String attachment);
//    public void getProjectTaskAssignees(String name);
//    public void getProjectTaskAssignee(String name, String assignee);
//    public void createProjectTaskAssignee(String name, String assignee);
//    public void deleteProjectTaskAssignee(String name, String assignee);
//    public void getProjectTaskDependencies(String name);
//    public void getProjectTaskDependency(String name, String dependency);
//    public void createProjectTaskDependency(String name, String dependency);
//    public void deleteProjectTaskDependency(String name, String dependency);
//    public void getProjectTaskSub
}
