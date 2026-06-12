package com.adanext2.service;

import com.adanext2.dto.request.CreateTaskRequest;
import com.adanext2.dto.response.TaskResponse;
import com.adanext2.exception.ResourceNotFoundException;
import com.adanext2.model.Project;
import com.adanext2.model.Task;
import com.adanext2.repository.ProjectRepository;
import com.adanext2.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;

    public TaskService(TaskRepository taskRepository,
                       ProjectRepository projectRepository) {
        this.taskRepository = taskRepository;
        this.projectRepository = projectRepository;
    }

    public TaskResponse create(CreateTaskRequest request) {
        Project project = projectRepository.findById(request.getProjectId())
            .orElseThrow(() -> new ResourceNotFoundException("Projet introuvable : " + request.getProjectId()));

        Task task = new Task();
        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setProject(project);
        taskRepository.save(task);
        return TaskResponse.from(task);
    }

    public List<TaskResponse> getByProject(Long projectId) {
        return taskRepository.findByProjectId(projectId)
            .stream()
            .map(TaskResponse::from)
            .collect(Collectors.toList());
    }

    public TaskResponse updateStatus(Long taskId, Task.Status newStatus) {
        Task task = taskRepository.findById(taskId)
            .orElseThrow(() -> new ResourceNotFoundException("Tâche introuvable : " + taskId));
        task.setStatus(newStatus);
        taskRepository.save(task);
        return TaskResponse.from(task);
    }
}