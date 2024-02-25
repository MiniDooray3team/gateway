package com.nhnacademy.springboot.gateway.controller;

import com.fasterxml.jackson.databind.annotation.JsonAppend;
import com.nhnacademy.springboot.gateway.adaptor.ProjectAdapter;
import com.nhnacademy.springboot.gateway.domain.Comment;
import com.nhnacademy.springboot.gateway.domain.Project;
import com.nhnacademy.springboot.gateway.domain.Task;
import com.nhnacademy.springboot.gateway.domain.TaskHeader;
import com.nhnacademy.springboot.gateway.dto.request.ProjectRegisterRequest;
import com.nhnacademy.springboot.gateway.dto.request.TaskRegisterRequest;
import com.nhnacademy.springboot.gateway.dto.request.TaskUpdateRequest;
import com.nhnacademy.springboot.gateway.dto.response.ProjectResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping({"","/projects"})
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectAdapter projectAdapter;
    @GetMapping
    public String projects(Model model) {
        // todo : account api 완료 시 주석 해제
        List<ProjectResponseDto> projectResponseDtoList = projectAdapter.getProjects();
        model.addAttribute("projects", projectResponseDtoList);
        return "project/projects";
    }


    @GetMapping("{projectId}/tasks")
    public String projectTasks(@PathVariable("projectId") Long projectId , Model model) {

        List<ProjectResponseDto> projectResponseDtoList = projectAdapter.getProjects();
        List<TaskHeader> taskHeaders = projectAdapter.getProjectTaskHeaders(projectId);
        Project project = projectAdapter.getProject(projectId);

        model.addAttribute("projects", projectResponseDtoList);
        model.addAttribute("selectedProject", project);
        model.addAttribute("tasks", taskHeaders);
        return "project/projects";
    }

    @GetMapping("{projectId}/tasks/{taskId}")
    public String projectTask(@PathVariable("projectId") Long projectId, @PathVariable("taskId") Long taskId, Model model) {
        List<ProjectResponseDto> projectResponseDtoList = projectAdapter.getProjects();
        List<TaskHeader> taskHeaders = projectAdapter.getProjectTaskHeaders(projectId);
        List<Comment> taskComments = projectAdapter.getTaskComments(projectId, taskId);
        Project project = projectAdapter.getProject(projectId);
        Task task = projectAdapter.getProjectTask(projectId, taskId);

        model.addAttribute("projects", projectResponseDtoList);
        model.addAttribute("tasks", taskHeaders);
        model.addAttribute("task", task);
        model.addAttribute("selectedProject", project);
        model.addAttribute("comments", taskComments);
        return "project/projects";
    }




    //todo : 프로젝트 생성 페이지
    @GetMapping("/register")
    public String registerProject() {
        return "project/register_form";
    }


    @PostMapping
    public String createProject(ProjectRegisterRequest projectRegisterRequest) {
        projectAdapter.createProject(projectRegisterRequest);
        return "redirect:/projects";
    }

    @PostMapping("{projectId}/tasks")
    public String createTask(TaskRegisterRequest taskRegisterRequest, @PathVariable("projectId") Long projectId) {
        projectAdapter.createProjectTask(projectId, taskRegisterRequest);
        return "redirect:/projects/" + projectId + "/tasks";
    }

    @PostMapping("{projectId}/tasks/{taskId}/comments")
    public String createTaskComment(@PathVariable("projectId") Long projectId, @PathVariable("taskId") Long taskId, String comment) {
        // todo : implement
     //   projectAdapter.createProjectTaskComment(projectId, taskId, comment);
        return "redirect:/projects/" + projectId + "/tasks/" + taskId + "/comments";
    }

    @GetMapping("{projectId}/settings")
    public String projectSettings(@PathVariable("projectId") Long projectId, Model model) {
        Project project = projectAdapter.getProject(projectId);
        model.addAttribute("project", project);
        return "project/settings";
    }

    @PutMapping("{projectId}/tasks/{taskId}")
    public String updateTask(@PathVariable("projectId") Long projectId, @PathVariable("taskId") Long taskId, TaskUpdateRequest taskUpdateRequest) {
        projectAdapter.updateProjectTask(projectId, taskId, taskUpdateRequest);
        return "redirect:/projects/" + projectId + "/tasks /" + taskId;
    }





}
