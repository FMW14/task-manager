package com.vtb.idrteam.taskmanager.services;

import com.vtb.idrteam.taskmanager.entities.Project;
import com.vtb.idrteam.taskmanager.repositories.ProjectRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProjectService {
    private ProjectRepository projectRepository;

    public List<Project> findAll(){
        return projectRepository.findAll();
    }

    public Optional<Project> findById(Long id){
        return projectRepository.findById(id);
    }

    public void deleteById(Long id){
        projectRepository.deleteById(id);
    }

    public Project saveOrUpdate(Project project){
        return projectRepository.save(project);
    }
}