package com.vtb.idrteam.taskmanager.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import com.vtb.idrteam.taskmanager.entities.Project;
import com.vtb.idrteam.taskmanager.entities.User;
import com.vtb.idrteam.taskmanager.exceptions.ProjectNotFoundException;
import com.vtb.idrteam.taskmanager.services.ProjectService;
import com.vtb.idrteam.taskmanager.services.TaskService;
import com.vtb.idrteam.taskmanager.services.UserService;
import com.vtb.idrteam.taskmanager.utils.Views;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/projects")
@AllArgsConstructor
@Slf4j
public class ProjectsController {
    private ProjectService projectService;


    @GetMapping
    @JsonView(Views.Small.class)
    public List<Project> getAllProjects(Principal principal) {
        return projectService.getAllProjectsByUsername(principal.getName());
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    @JsonView(Views.Small.class)
    public Project createNewProject(@RequestBody Project project, Principal principal) {
        return projectService.createNewProject(project, principal.getName());
    }

    @GetMapping("/{id}")
    @JsonView(Views.BigProject.class)
    public Project getProjectById(@PathVariable Long id,Principal principal){
        return projectService.findById(id).orElseThrow( () -> new ProjectNotFoundException(String.format("Project with id = %d not found!",id)));
    }
}
