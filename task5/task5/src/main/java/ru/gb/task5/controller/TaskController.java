package ru.gb.task5.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import ru.gb.task5.model.Task;
import ru.gb.task5.repositories.TaskRepository;
import ru.gb.task5.status.TaskStatus;
@RestController
@RequestMapping("/tasks")
@AllArgsConstructor

public class TaskController {
    private final TaskRepository taskRepository;
    
    @PostMapping
    public Task addTask(@RequestBody Task task){
        task.setCreationDate(LocalDateTime.now());
        return taskRepository.save(task);
    }
    @GetMapping
    public List<Task> getAllTasks(){
        return taskRepository.findAll();
    }
    @GetMapping("/status/{status}")
    public List<Task> getTasksByStatus(@PathVariable TaskStatus status){
        return taskRepository.findByStatus(status);
    }

    @PutMapping("/{id}")
    public Task updateTaskStatus(@PathVariable Long id, @RequestBody Task task){
        Task existingTask = taskRepository.findById(id).orElse(null);
        if(existingTask != null){
            existingTask.setStatus(task.getStatus());
            return taskRepository.save(existingTask);
        } else {
            return null;
        }}

        @DeleteMapping("/{id}")
        public void deleteTask(@PathVariable Long id){
            taskRepository.deleteById(id);
        }


}

