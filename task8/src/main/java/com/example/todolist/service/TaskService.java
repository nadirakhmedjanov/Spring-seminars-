package com.example.todolist.service;

import com.example.todolist.aspect.TrackUserAction;
import com.example.todolist.exception.ResourceNotFoundException;
import com.example.todolist.model.Task;
import com.example.todolist.model.TaskStatus;
import com.example.todolist.repository.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
@AllArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;

    
    @TrackUserAction
    public Task addTask(Task task) {
        task.setCreatedAt(LocalDateTime.now());
        task.setStatus(TaskStatus.NOT_STARTED);
        return taskRepository.save(task);
    }

    
    @TrackUserAction
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    
    @TrackUserAction
    public List<Task> getTasksByStatus(TaskStatus status) {
        return taskRepository.findAllByStatus(status);
    }

    
    @TrackUserAction
    public Task updateTaskStatus(Long id, TaskStatus status) {
        Task task = taskRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Invalid task ID"));
        task.setStatus(status);
        return taskRepository.save(task);
    }

    
    public Task updateTask(Long id, Task updateTask) {
        Task task = taskRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Invalid task ID"));
        task.setDescription(updateTask.getDescription());
        //task.setStatus(updateTask.getStatus());
        return taskRepository.save(task);
    }

    
    @TrackUserAction
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    
    public Task getTaskById(Long id) {
        return taskRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Invalid task ID"));
    }
}
