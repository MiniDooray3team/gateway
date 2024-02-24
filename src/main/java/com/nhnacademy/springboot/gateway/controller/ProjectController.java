package com.nhnacademy.springboot.gateway.controller;

import com.nhnacademy.springboot.gateway.adaptor.ProjectAdapter;
import com.nhnacademy.springboot.gateway.domain.Task;
import com.nhnacademy.springboot.gateway.domain.TaskHeader;
import com.nhnacademy.springboot.gateway.dto.request.ProjectRegisterRequest;
import com.nhnacademy.springboot.gateway.dto.request.TaskRegisterRequest;
import com.nhnacademy.springboot.gateway.dto.response.ProjectResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

        model.addAttribute("projects", projectResponseDtoList);
        model.addAttribute("tasks", taskHeaders);
        return "project/projects";
    }

    @GetMapping("{projectId}/tasks/{taskId}")
    public String projectTask(@PathVariable("projectId") Long projectId, @PathVariable("taskId") Long taskId, Model model) {
        List<ProjectResponseDto> projectResponseDtoList = projectAdapter.getProjects();
        List<TaskHeader> taskHeaders = projectAdapter.getProjectTaskHeaders(projectId);
        Task task = projectAdapter.getProjectTask(projectId, taskId);

        model.addAttribute("projects", projectResponseDtoList);
        model.addAttribute("tasks", taskHeaders);
        model.addAttribute("task", task);
        return "project/projects";
    }


    //todo : 프로젝트 생성 페이지
    @GetMapping("/register")
    public String registerProject() {
        return "project/register_form";
    }


    // todo : 프로젝트 생성
    // 프로젝트 생성 페이지?
    @PostMapping
    public String createProject(ProjectRegisterRequest projectRegisterRequest) {
        System.out.println(projectRegisterRequest.getProjectStatusId());
        System.out.println(projectRegisterRequest.getName());
        //projectAdapter.createProject(projectRegisterRequest);
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

    @GetMapping("/settings")
    public String projectSettings() {
        return "project/settings";
    }







}
